package main;

public class Main {

	
	public static void main(String[] args) {
		System.setProperty("phantomjs.binary.path", "C:\\Users\\GCC\\Desktop\\phantomjs.exe");
		SpiderController sc  = new SpiderController();
		sc.setThreads(5).addSeed("http://www.douyu.com").init().start();

	}
	
	
}
