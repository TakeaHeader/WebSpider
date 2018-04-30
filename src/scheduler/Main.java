package scheduler;

public class Main {

	
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "F:/GitHub/WebSpider/src/resource/chromedriver.exe");
		SpiderController sc  = new SpiderController();
		sc.setThreads(3).addSeed("https://weibo.com/guodegang").init().start();
	}
	
	
}
