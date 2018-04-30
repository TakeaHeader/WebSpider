package parser;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import quenu.Queue;

public class SimpleDoucumentHandler implements DocumentHandler<List<WebElement>> {
	
	
	private Logger log = Logger.getLogger(getClass());

/*	@Override
	public Object HandDocument(String doc, Queue<String> queue)
			throws Exception {
		Pattern p = Pattern.compile("[a-zA-z]+://[^\\s^\"]*");
		Matcher m = p.matcher(doc);
		while (m.find()) {
			String temp = m.group();
			queue.addQueue(temp);
			log.debug(temp);
		}
		return null;
	}
*/
	@Override
	public Object HandDocument(List<WebElement> content, Queue<String> urlqueue)
			throws Exception {
		for (Iterator iterator = content.iterator(); iterator.hasNext();) {
			WebElement webElement = (WebElement) iterator.next();
			String href = webElement.getAttribute("href");
			if(href != null){
				if(href.indexOf("http") != -1 && href.indexOf("weibo.com") != -1){
					System.out.println(href);
				}
			}
		}
		return null;
	}

	
	
}
