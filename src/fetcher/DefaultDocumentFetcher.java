package fetcher;

import java.net.URL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class DefaultDocumentFetcher extends AbstractFetcher<Document>{
	
	

	@Override
	public Document getPage(String Url) throws Exception {
		WebDriver driver = getDriver();
		driver.navigate().to(new URL(Url));
		try{
			Thread.sleep(3000);
		}catch(InterruptedException ie){
			log.debug("InterruptedException!");
		}
		JavascriptExecutor jse =(JavascriptExecutor)driver;
		jse.executeScript("window.scrollTo(0,10000)");
		return Jsoup.parse(driver.getPageSource());
	}
	
	
	

}
