package parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import quenu.Queue;

public class SimpleDoucumentHandler implements DocumentHandler<String> {
	
	
	private Logger log = Logger.getLogger(getClass());

	@Override
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

	
	
}
