package spider;

import org.jsoup.nodes.Document;
import parser.DocumentHandler;
import intercept.Intecepter;
import quenu.Queue;
import fetcher.Fetcher;

public class SpiderBuilder {
	
	private Fetcher<Document> fetcher;
	
	private Queue<String> queue;
	
	private Intecepter intecept;
	
	private DocumentHandler<Document> handler;
	
	public SpiderBuilder() {}
	
	public SpiderBuilder SetFetcher(Fetcher<Document> fetcher){
		this.fetcher = fetcher;
		return this;
	}
	
	public SpiderBuilder SetQueue(Queue<String> queue){
		this.queue = queue;
		return this;
	}
	
	public SpiderBuilder SetHandler(DocumentHandler<Document> handler){
		this.handler = handler;
		return this;
	}
	
	public SpiderBuilder SetIntecepter(Intecepter intecept){
		this.intecept = intecept;
		return this;
	}
	
	public Runnable build(){
		return new Spider(this);
	}
	
	public Fetcher<Document> getFetcher() {
		return fetcher;
	}

	public Intecepter getIntecepter() {
		return intecept;
	}
	
	public Queue<String> getQueue() {
		return queue;
	}
	
	public DocumentHandler<Document> getHandler() {
		return handler;
	}

}
