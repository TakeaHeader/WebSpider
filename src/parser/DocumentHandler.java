package parser;

import java.util.List;

import org.jsoup.nodes.Document;


public interface DocumentHandler<T>{
	
	
	Object HandDocument(Document doc,List<T> seeds) throws Exception;
	
	
}
