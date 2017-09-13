package downloader;

import java.io.FileNotFoundException;
import org.apache.http.Header;

public class SimpleDownLoader extends BaseDownloader{
	
	
	public static void main(String[] args) throws FileNotFoundException {
		//Downloader down = new SimpleDownLoader();
		//down.DownloadUrl("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3034064908,501733989&fm=111&gp=0.jpg", "C:\\Users\\WJ\\Desktop\\file\\1.jpg");
	}

	@Override
	public boolean CheckContentType(Header header, String url) {
		// do sth to check file
		return true;
	}

	
	
}


