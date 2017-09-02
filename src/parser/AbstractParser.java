package parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public abstract class AbstractParser implements Parser{
	
	protected Document document;
	
	public AbstractParser() {
	}
	
	public void setContent(String content){
		this.document = Jsoup.parse(content);
	}
	
	public AbstractParser(String content) {
		this.document = Jsoup.parse(content);
	}

	@Override
	public String evalTitle() {
		Elements elements  = document.getElementsByTag("title");
		if(elements.size() != 0){
			return elements.first().text();
		}
		return String.valueOf("Œﬁ±ÍÃ‚");
	}
	
	
	

}
