package fetcher;

public interface Fetcher<T>{
	
	
	
	public T getPageHtml(String Url) throws Exception;
	
	public T getPageContent(String Url) throws Exception;
	
	
	
	
	
	
	
}
