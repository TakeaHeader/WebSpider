package intercept;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;

public class DefaultIntercepter implements Intecepter{
	
	private final Logger log = Logger.getLogger(getClass());

	@Override
	public boolean afterGetSeed(String Url) {
		return true;
	}

	@Override
	public void afterHanlerDocument(Document doc) {
		
	}

	@Override
	public void HandlerResult(Object result) {
	}

}
