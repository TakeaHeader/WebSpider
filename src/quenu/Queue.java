package quenu;

public interface Queue <T> {
	
	public boolean contain(T t);
	
	public T pop();
	
	public int QueueSize();
	
	public void addQueue(T t);
	

}
