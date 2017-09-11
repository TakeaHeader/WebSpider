package spider;

import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;

import aspact.Intecepter;
import aspact.DefaultIntercepter;
import parser.DocumentHandler;
import parser.Parser;
import parser.SimpleDoucumentHandler;
import fetcher.Fetcher;

public class UrlSpider implements Runnable{
	private final Logger log = Logger.getLogger(UrlSpider.class);
	
	private Fetcher<Document> fetcher;
	
	private List<String> seeds;
	
	private DocumentHandler<String> handler  = null ;
	
	private final Intecepter asp = new DefaultIntercepter();

	public UrlSpider(Fetcher<Document> fetcher, Parser parser,
			List<String> seeds) {
		this.fetcher = fetcher;
		this.seeds = seeds;
		this.handler = new SimpleDoucumentHandler();
	}

	public void SetHandler(DocumentHandler<String> handler) {
		this.handler = handler;
    }

	@Override
	public void run() {
//		蜘蛛的整个运行过程,交由AspIntercepter去做中间的一些处理
		while(true){
			String url = null;
			synchronized (seeds) {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				while(seeds.size() == 0){
					try {
						seeds.notifyAll();
						log.debug("Thread:"+Thread.currentThread().getName()+"  is waiting!");
						seeds.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
//				去掉第一个并返回值
				url = seeds.remove(0);
//				必须返回true值
				if(!asp.afterGetSeed(url)) {
					continue;
				}
			}
			try {
				log.debug("Getting Html "+url);
//				获取HTML返回Document
				Document content = fetcher.getPageContent(url);
				asp.afterHanlerDocument(content);
//				交给DocumentHandler去处理
				Object obj = handler.HandDocument(content,seeds);
				asp.HandlerResult(obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	}

	
	
	
	
	
	
}
