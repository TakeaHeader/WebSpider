package aspact;

import org.jsoup.nodes.Document;

public interface Intecepter {
	
//	在获取种子之后
	public boolean afterGetSeed(String Url);
	
//	处理文档之前
	public void beforeHanlerDocument(Document doc);
	
//	处理文档之后
	public void afterHanlerDocument(Document doc);
	
//	处理返回的结果
	public void HandlerResult(Object result);
	
}
