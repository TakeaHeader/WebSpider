package scheduler;

import intercept.DefaultIntercepter;
import intercept.Intecepter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import parser.DocumentHandler;
import parser.SimpleDoucumentHandler;
import quenu.Queue;
import quenu.SingleQueue;
import fetcher.DefaultDocumentFetcher;
import fetcher.Fetcher;
import spider.SpiderBuilder;

public class SpiderController {
	
	private Logger log = Logger.getLogger(getClass());

	private int threads = 3;
	
	private ExecutorService service;
	
	private Fetcher<Document> fetcher = new DefaultDocumentFetcher();
	
	private Queue<String> queue = new SingleQueue<String>();
	
	private Intecepter intercept = new DefaultIntercepter();
	
	private DocumentHandler<Document> handler = new SimpleDoucumentHandler();
	
	public SpiderController() {
		log.debug("The Spider is starting...");
	}
	
	public SpiderController setThreads(int threads) {
		this.threads = threads;
		return this;
	}
	
	public SpiderController init(){
		this.service = Executors.newFixedThreadPool(threads);
		return this;
	}
	
	public SpiderController addSeed(String url){
		log.debug("The Spider is adding seed " +url);
		this.queue.addQueue(url);
		return this;
	}
	
	public void start(){
		Runnable builder = new SpiderBuilder()
			.SetFetcher(fetcher)
			.SetQueue(queue)
			.SetIntecepter(intercept)
			.SetHandler(handler)
			.build();
		for(int i = 0; i < threads; i ++){
				this.service.execute(builder);
		}
	}
	
	
}