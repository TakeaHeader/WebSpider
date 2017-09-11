package main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import parser.Parser;
import parser.ParserImpl;
import fetcher.Fetcher;
import fetcher.FetcherImpl;
import spider.UrlSpider;


public class SpiderController {
	
	private Logger log = Logger.getLogger(getClass());

	private int threads = 3;
	
	private ExecutorService service;
	
	private Fetcher<Document> fetcher = new FetcherImpl();
	
	private Parser parser = new ParserImpl();
	
	private List<String> seeds = new ArrayList<String>();
	
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
		this.seeds.add(url);
		return this;
	}
	
	public void start(){
		for(int i = 0; i < threads; i ++){
			this.service.execute(new UrlSpider(fetcher,parser,seeds));
		}
	}
	
	
}
