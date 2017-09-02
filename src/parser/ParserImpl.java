package parser;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.select.Elements;

import downloader.Downloader;
import downloader.SimpleDownLoader;

public class ParserImpl extends AbstractParser{
	
	private final String location = "C:/Users/GCC/Desktop/img/";
	
	private Downloader downloader = new SimpleDownLoader();

	public ParserImpl() {
	}
	
	public ParserImpl(String content) {
		super(content);
	}
	
	@Override
	public void setContent(String content) {
		super.setContent(content);
	}

	@Override
	public List<String> hrefs() {
		List<String> list = new ArrayList<String>();
		Elements elements = document.getElementsByTag("img");
		Elements as = document.getElementsByTag("a");
		for (int i = 0; i < as.size(); i++) {
			if(as.get(i).attr("href").startsWith("/")){
				list.add("https://www.zhihu.com"+as.get(i).attr("href"));
			}
		}
		for (int i = 0; i < elements.size(); i++) {
			if(elements.get(i).attr("src").startsWith("http")){
				String string = elements.get(i).attr("src");
				int index = string.lastIndexOf("/");
				String newstr = string.substring(index+1, string.length());
				downloader.DownloadUrl(string, location+newstr);
			}
		}
		return list;
	}

}
