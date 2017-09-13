package quenu;

import java.util.LinkedList;

import org.eclipse.jetty.util.ConcurrentHashSet;

public class SingleQueue<T> extends BaseQueue<T>{
	
	private final ConcurrentHashSet<Integer> hashset = new ConcurrentHashSet<Integer>();
	
	private final LinkedList<T> list = new LinkedList<T>();

	@Override
	public boolean contain(T t) {
		int hash = t.hashCode();
		return hashset.contains(hash);
	}

	@Override
	public T pop() {
		return list.pollFirst();
	}

	@Override
	public int QueueSize() {
		return list.size();
	}

	@Override
	public void addQueue(T t) {
		int hash = t.hashCode();
		if(!hashset.contains(hash)){
			this.hashset.add(hash);
			this.list.add(t);
		}
	}
	
	

}
