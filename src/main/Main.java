package main;

public class Main {

	
	public static void main(String[] args) {
		SpiderController sc  = new SpiderController();
		sc.setThreads(10).addSeed("https://www.tokyo-hot.com").init().start();
		
	}
	
	
}
