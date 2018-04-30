package fetcher;

public interface Fetcher<T>{
	
	public T getPage(String Url) throws Exception;
	
}
