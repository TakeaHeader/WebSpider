package intercept;


public interface Intecepter {
	
//	在获取种子之后
	public boolean beforeFetchUrl(String Url);
	
//	处理文档之后
	public void afterHanlerDocument(Object content);
	
//	处理返回的结果
	public void HandlerResult(Object result);

	void afterHanlerDocument(String doc);
	
}
