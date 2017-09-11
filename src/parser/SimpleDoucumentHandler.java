package parser;

import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SimpleDoucumentHandler implements DocumentHandler<String> {

	@Override
	public Object HandDocument(Document doc,List<String> seeds) throws Exception {
		Elements as = doc.getElementsByTag("a");
		for (Element elemen : as) {
			String href = elemen.attr("href");
			if(href.startsWith("https://www.lagou.com/zhaopin")){
				seeds.add(href);
			}
		}
		return null;
	}

	
	
}
