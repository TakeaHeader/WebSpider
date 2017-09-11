package fetcher;

import java.net.URL;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class FetcherImpl implements Fetcher<Document>{
	
	private final Logger log = Logger.getLogger(getClass());
	
	private WebDriver driver = null;
	
	public FetcherImpl() {
		ChromeOptions chrome = new ChromeOptions();
		//chrome.addArguments("--headless","--disable-gpu");
		driver = new ChromeDriver(chrome);
	}
	
	@Override
	public Document getPageContent(String Url) throws Exception{
		driver.navigate().to(new URL(Url));
		JavascriptExecutor jse =(JavascriptExecutor)driver;
		jse.executeScript("window.scrollTo(0,10000)");
		Thread.sleep(1000);
		String content = driver.getPageSource();
		synchronized (driver) {
			int temp = 10 ;
			while(temp != 0) {
				List<WebElement> list = driver.findElements(By.className("con_list_item"));
				if(list.size() == 0){
					break;
				}
				for (WebElement webElement : list) {
					log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
					log.debug("\n"+webElement.getText());
				}
				WebElement element = driver.findElement(By.xpath("//*[@id=\"s_position_list\"]/div[2]/div/a[6]"));
				element.click();
				temp --;
			}
		}
		return Jsoup.parse(content);
	}
	
	

}
