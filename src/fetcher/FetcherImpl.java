package fetcher;

import java.net.URL;
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
		chrome.addArguments("--headless","--disable-gpu");
		driver = new ChromeDriver(chrome);
	}
	
	@Override
	public Document getPageContent(String Url) throws Exception{
		log.debug("Get Document Url:"+ Url);
		driver.navigate().to(new URL(Url));
		JavascriptExecutor jse =(JavascriptExecutor)driver;
		jse.executeScript("window.scrollTo(0,10000)");
		Thread.sleep(5000);
		WebElement frame = driver.findElement(By.xpath("//*[@id=\"login_frame\"]"));
		WebDriver dv = driver.switchTo().frame(frame);
		WebElement elm = dv.findElement(By.xpath("//*[@id=\"switcher_plogin\"]"));
		elm.click();
		WebElement el = dv.findElement(By.xpath("//*[@id=\"u\"]"));
		el.sendKeys("1142482404");
		WebElement ele = dv.findElement(By.xpath("//*[@id=\"p\"]"));
		ele.sendKeys("googlezg1");
		WebElement eleme = dv.findElement(By.xpath("//*[@id=\"login_button\"]"));
		eleme.click();
		driver.navigate().to("https://user.qzone.qq.com/1142482404");
		String content = driver.getPageSource();
		//System.setOut(new PrintStream(new File("C:\\Users\\WJ\\Desktop\\1.txt")));
		System.out.println(content);
		return Jsoup.parse(content);
	}
	
	
	public static void main(String[] args) throws Exception {
		System.setProperty("webdriver.chrome.driver", "F:/workspace/WebSpider/src/resource/chromedriver.exe");
		FetcherImpl f = new FetcherImpl();
		f.getPageContent("https://qzone.qq.com/");
		
	}
	

}
