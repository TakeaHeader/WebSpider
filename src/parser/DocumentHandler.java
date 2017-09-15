package parser;

import quenu.Queue;


public interface DocumentHandler<T>{
	

	Object HandDocument(String doc, Queue<String> queue) throws Exception;
	
	
}
