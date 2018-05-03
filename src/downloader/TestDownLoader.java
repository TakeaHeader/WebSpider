package downloader;

public class TestDownLoader {
	
	
	public static void main(String[] args) {
		Downloader downloader = new SimpleDownLoader();
		downloader.DownloadUrl("https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=3656872103,1871879965&fm=58&s=19207D3285E7E10348C002ED02007026&bpow=121&bpoh=75", "C:/Users/Admin/Desktop/1.png");
	}

}
