package downloader;

import io.IOUtils;
import java.io.IOException;
import java.util.Properties;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public abstract class BaseDownloader implements Downloader{
	
	protected Logger log = Logger.getLogger(getClass());
	
	private final HttpClient client = HttpClients.custom().build();
	
	@Override
	public void setProperties(Properties prop) {
		
	}

	@Override
	public void DownloadUrl(String url, String location) {
		HttpGet get = new HttpGet(url);
		HttpResponse response = null;
		try {
			response = client.execute(get);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(response == null){
			return ;
		}
		HttpEntity entity= response.getEntity();
		Header head = entity.getContentType();
		if(!CheckContentType(head,url)){
			return ;
		}
		log.debug("download file ContentType:"+ head.getValue());
		try {
			byte [] b = EntityUtils.toByteArray(entity);
			IOUtils.Download(b, location);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			get.releaseConnection();
		}
	}
	
	/**
	 * 
	 * @param header Content-Type header
	 * @param url  download Url
	 * @return true is needed,if false the url will not be download
	 */
	public abstract boolean CheckContentType(Header header,String url);

	
	
	
	
}
