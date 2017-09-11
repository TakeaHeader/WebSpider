package aspact;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;

public class DefaultIntercepter implements Intecepter{
	
	private final Logger log = Logger.getLogger(getClass());

	@Override
	public boolean afterGetSeed(String Url) {
		return true;
	}

	@Override
	public void beforeHanlerDocument(Document doc) {
	}

	@Override
	public void afterHanlerDocument(Document doc) {
		
	}

	@Override
	public void HandlerResult(Object result) {/*
		if(result instanceof Map) {
			Map<Element,String> map = (Map)result;
			Set<Element> set = map.keySet();
			for (Element element : set) {
				log.debug("deatil page:"+map.get(element));
			}
		}
		
	*/}

}
