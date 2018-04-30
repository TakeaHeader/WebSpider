package parser;

import quenu.Queue;


public interface DocumentHandler<T>{
	
	Object HandDocument(T content, Queue<String> urlqueue)  throws Exception;
	
	
}
