package spider;

import intercept.InteceptProxy;
import intercept.Intecepter;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;

import parser.DocumentHandler;
import quenu.Queue;
import fetcher.Fetcher;

public class Spider implements Runnable{
	
	private Logger log = Logger.getLogger(getClass());
	
	private Fetcher<Document> fetcher;
	
	private Queue<String> urlqueue ;
	
	private DocumentHandler<Document> handler  ;
	
	private Intecepter intcept ;
	
	private final Object lock = new Object();

	public Spider(SpiderBuilder builder) {
		this.fetcher   =  builder.getFetcher();
		this.handler   =  builder.getHandler();
		this.urlqueue  =  builder.getQueue();
		this.intcept   =  InteceptWarpper.newproxyinstance(builder.getIntecepter());
	}

	@Override
	public void run() {
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
				url = urlqueue.pop();
				if(!intcept.beforeFetchUrl(url)) {
					continue;
				}
			}
			try {
				Document content = fetcher.getPage(url);
				intcept.afterHanlerDocument(content);
				Object obj = handler.HandDocument(content,urlqueue);
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
