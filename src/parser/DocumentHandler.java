package parser;

import org.jsoup.nodes.Document;
import quenu.Queue;


public interface DocumentHandler<T>{
	
	
	Object HandDocument(Document doc,Queue<T> queue) throws Exception;
	
	
}
