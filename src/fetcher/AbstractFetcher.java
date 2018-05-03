package fetcher;

/*import java.util.concurrent.TimeUnit;
*/
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public abstract class AbstractFetcher<T> implements Fetcher<T>{
	
	private final ThreadLocal<WebDriver> locals = new ThreadLocal<>();
	
	protected final Logger log = Logger.getLogger(getClass());
	
	protected WebDriver getDriver(){
		WebDriver driver = locals.get();
		if(driver == null){
			ChromeOptions chrome = new ChromeOptions();
			//chrome.addArguments("--headless","--disable-gpu");
			driver = new ChromeDriver(chrome);
			/*driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);*/
			/*driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);*/
			locals.set(driver);
		}
		return driver;
	}
	

}
