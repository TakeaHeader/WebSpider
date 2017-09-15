package intercept;

import org.apache.log4j.Logger;

public class DefaultIntercepter implements Intecepter{
	
	private final Logger log = Logger.getLogger(getClass());

	@Override
	public boolean beforeFetchUrl(String Url) {
		return true;
	}

	@Override
	public void afterHanlerDocument(String doc) {
		
	}

	@Override
	public void HandlerResult(Object result) {
	}

}
