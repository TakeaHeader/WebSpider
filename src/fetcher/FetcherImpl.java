package fetcher;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

public class FetcherImpl implements Fetcher<String>{
	
	private Logger log = Logger.getLogger(getClass());
	
	private final static String PHANTOMJS = "C:/Users/GCC/Desktop/phantomjs.exe";
	
	private final static String SCRIPT = "C:/Users/GCC/Desktop/get.js";
	
	private final WebDriver driver = new PhantomJSDriver();

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
	
	@Override
	public String getPageContent(String Url) throws Exception{
		driver.navigate().to(new URL(Url));
		JavascriptExecutor jse =(JavascriptExecutor)driver;
		jse.executeScript("window.scrollTo(0,10000)");
		String content = driver.getPageSource();
		return content;
	}
	

	
	
	

}
