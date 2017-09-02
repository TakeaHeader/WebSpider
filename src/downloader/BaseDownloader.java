package downloader;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;

public abstract class BaseDownloader implements Downloader{
	
	protected Logger log = Logger.getLogger(getClass());
	
	public InputStream GetInputStream(String url) {
		String Type = "";
		URLConnection conn = null;
		try{
			URL Url = new URL(url);
			conn = (HttpURLConnection)Url.openConnection();
			Type = conn.getContentType();
		}catch(Exception e){
			log.error("[Error]:"+e.getMessage());
		}
		log.debug("The DownLoader is downloading url:"+url+";Type:"+Type);
		InputStream in = null;
		try {
			in = conn.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return in;
	}
	
	
	
}
