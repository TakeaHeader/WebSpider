package spider;

import parser.DocumentHandler;
import intercept.Intecepter;
import quenu.Queue;
import fetcher.Fetcher;

public class UrlSpiderBuilder {
	
	private Fetcher fetcher;
	
	private Queue<String> queue;
	
	private Intecepter intecept;
	
	private DocumentHandler<?> handler;
	
	public UrlSpiderBuilder() {}
	
	public UrlSpiderBuilder SetFetcher(Fetcher fetcher){
		this.fetcher = fetcher;
		return this;
	}
	
	public UrlSpiderBuilder SetQueue(Queue<String> queue){
		this.queue = queue;
		return this;
	}
	
	public UrlSpiderBuilder SetHandler(DocumentHandler<?> handler){
		this.handler = handler;
		return this;
	}
	
	public UrlSpiderBuilder SetIntecepter(Intecepter intecept){
		this.intecept = intecept;
		return this;
	}
	
	public Runnable build(){
		return new UrlSpider(this);
	}
	
	public Fetcher getFetcher() {
		return fetcher;
	}

	public Intecepter getIntecepter() {
		return intecept;
	}
	
	public Queue<String> getQueue() {
		return queue;
	}
	
	public DocumentHandler<?> getHandler() {
		return handler;
	}

}
