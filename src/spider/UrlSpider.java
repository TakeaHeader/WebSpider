package spider;

import intercept.InteceptProxy;
import intercept.Intecepter;

import org.apache.log4j.Logger;

import parser.DocumentHandler;
import quenu.Queue;
import fetcher.Fetcher;

public class UrlSpider implements Runnable{
	
	private final Logger log = Logger.getLogger(UrlSpider.class);
	
	private Fetcher fetcher;
	
	private Queue<String> urlqueue ;
	
	private DocumentHandler<?> handler  ;
	
	private Intecepter intcept ;
	
	private final Object lock = new Object();

	public UrlSpider(UrlSpiderBuilder builder) {
		this.fetcher   =  builder.getFetcher();
		this.handler   =  builder.getHandler();
		this.urlqueue  =  builder.getQueue();
		this.intcept   =  InteceptWarpper.newproxyinstance(builder.getIntecepter());
	}

	@Override
	public void run() {
//		蜘蛛的整个运行过程,交由AspIntercepter去做中间的一些处理
		while(true){
			String url = null;
			synchronized (lock) {
				while(urlqueue.QueueSize() == 0){
					try {
						lock.notifyAll();
						log.debug("Thread:"+Thread.currentThread().getName()+"  is waiting!");
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}	
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
//				去掉第一个并返回值
				url = urlqueue.pop();
//				必须返回true值
				if(!intcept.beforeFetchUrl(url)) {
					continue;
				}
			}
			try {
				log.debug("Getting Html "+url);
//				获取HTML返回Document
				String content = fetcher.getPageContent(url);
				intcept.afterHanlerDocument(content);
//				交给DocumentHandler去处理
				Object obj = null;
				synchronized (lock) {
					obj = handler.HandDocument(content,urlqueue);
				}
				intcept.HandlerResult(obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	
	private static class InteceptWarpper {
		
		private static InteceptProxy ip = new InteceptProxy();
		
		public static Intecepter newproxyinstance(Intecepter intercept) {
			ip.setIntcept(intercept);
			return ip.newInstance();
		}
		
		
		
	}
	
	
	
	
}
