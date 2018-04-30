package rule;


/***
 * 
 * @author Admin  规则(主要是为了Url匹配定制规则,以及document文档 的元素配置规则)
 *
 */
public interface Rule<T>{
	
	
	public boolean match(T t);
	
	
}
