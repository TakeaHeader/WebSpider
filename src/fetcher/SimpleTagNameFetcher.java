package fetcher;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SimpleTagNameFetcher implements Fetcher<List<WebElement>>{
	
	private final ThreadLocal<WebDriver> locals = new ThreadLocal<>();
	
	private final Logger log = Logger.getLogger(getClass());
	
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
	
	
	private WebDriver getDriver(){
		WebDriver driver = locals.get();
		if(driver == null){
			ChromeOptions chrome = new ChromeOptions();
			chrome.addArguments("--headless","--disable-gpu");
			driver = new ChromeDriver(chrome);
			driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
			locals.set(driver);
		}
		return driver;
	}
	
}
