package fetcher;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

public class FetcherImpl implements Fetcher<String>{
	
	private Logger log = Logger.getLogger(getClass());
	
	private final String PHANTOMJS = "C:/Users/GCC/Desktop/phantomjs.exe";
	
	private final String SCRIPT = "C:/Users/GCC/Desktop/get.js";

	@Override
	public String getPageHtml(String Url) throws Exception {
		log.debug("The Spider is fetching url <<<"+Url+">>>");
		Runtime run = Runtime.getRuntime();
		Process process = run.exec(PHANTOMJS+" --load-images=false "+SCRIPT+" "+Url);
		InputStream in = process.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8")); 
        String tmp = "";  
        StringBuilder sb = new StringBuilder();
        while((tmp = br.readLine())!=null){  
        	sb.append(tmp);
        }  
		return sb.toString();
	}
	
	

}
