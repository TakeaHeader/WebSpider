package scheduler;

public class Main {

	
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "F:/workspace/WebSpider/src/resource/chromedriver.exe");
		SpiderController sc  = new SpiderController();
		sc.setThreads(5).addSeed("http://qq.yh31.com/zjbq/0551964.html").init().start();
	}
	
	
}
