package downloader;

import org.apache.http.Header;

public class SimpleDownLoader extends BaseDownloader{
	

	@Override
	public boolean CheckContentType(Header header, String url) {
		// do sth to check file
		return true;
	}

	
	
}


