package fetcher;

import java.net.URL;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class FetcherImpl implements Fetcher{
	
	private final Logger log = Logger.getLogger(getClass());
	
	private WebDriver driver = null;
	
	public FetcherImpl() {
		ChromeOptions chrome = new ChromeOptions();
		chrome.addArguments("--headless","--disable-gpu");
		driver = new ChromeDriver(chrome);
	}
	
	@Override
	public String getPageContent(String Url) throws Exception{
		log.debug("Get Document Url:"+ Url);
		driver.navigate().to(new URL(Url));
		JavascriptExecutor jse =(JavascriptExecutor)driver;
		jse.executeScript("window.scrollTo(0,10000)");
		String content = driver.getPageSource();
		return content;
	}
	
	
}
