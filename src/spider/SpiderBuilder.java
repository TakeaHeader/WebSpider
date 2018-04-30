package spider;

import java.util.List;

import org.openqa.selenium.WebElement;

import parser.DocumentHandler;
import intercept.Intecepter;
import quenu.Queue;
import fetcher.Fetcher;

public class SpiderBuilder {
	
	private Fetcher<List<WebElement>> fetcher;
	
	private Queue<String> queue;
	
	private Intecepter intecept;
	
	private DocumentHandler<List<WebElement>> handler;
	
	public SpiderBuilder() {}
	
	public SpiderBuilder SetFetcher(Fetcher<List<WebElement>> fetcher){
		this.fetcher = fetcher;
		return this;
	}
	
	public SpiderBuilder SetQueue(Queue<String> queue){
		this.queue = queue;
		return this;
	}
	
	public SpiderBuilder SetHandler(DocumentHandler<List<WebElement>> handler){
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
	
	public Fetcher<List<WebElement>> getFetcher() {
		return fetcher;
	}

	public Intecepter getIntecepter() {
		return intecept;
	}
	
	public Queue<String> getQueue() {
		return queue;
	}
	
	public DocumentHandler<List<WebElement>> getHandler() {
		return handler;
	}

}
