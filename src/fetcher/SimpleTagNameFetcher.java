package fetcher;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SimpleTagNameFetcher extends AbstractFetcher<List<WebElement>>{
	
	
	public SimpleTagNameFetcher() {}
	
	@Override
	public List<WebElement> getPage(String Url) throws MalformedURLException, FileNotFoundException{
		WebDriver driver = getDriver();
		driver.navigate().to(new URL(Url));
		try{
			Thread.sleep(7000);
		}catch(InterruptedException ie){
			log.debug("InterruptedException!");
		}
		JavascriptExecutor jse =(JavascriptExecutor)driver;
		jse.executeScript("window.scrollTo(0,10000)");
		return driver.findElements(By.tagName("a"));
	}
	
	
}
