package rule;
/***
 * 
 * @author Admin 规则集(用于添加规则集合)
 *
 * @param <T>
 */

public interface RuleSet <T>{
	
	
	public void addRule(Rule<T> rule);
	
	public void execute();
	

}
