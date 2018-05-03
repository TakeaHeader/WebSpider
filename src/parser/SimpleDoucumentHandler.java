package parser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import quenu.Queue;

public class SimpleDoucumentHandler implements DocumentHandler<Document> {
	
	private Logger log = Logger.getLogger(getClass());
	
	private SqlSession session = null;

	public SimpleDoucumentHandler(){
		synchronized (this) {
			if(session == null){
				session = getSqlSession();
			}
		}
	}
	
	@Override
	public Object HandDocument(Document content, Queue<String> urlqueue)
			throws Exception {
		Elements element = content.getElementsByTag("a");
		for (Element ele : element) {
			String href = ele.attr("href");
			if(href != null && href.startsWith("http") && href.indexOf("www.360wa.com") != -1 ){
				Map<String, String> param = new HashMap<>();
				param.put("URL", href);
				Map map = session.selectMap("huajiface.selectUrl", param,"URL");
				if(map.size() == 0){
					session.insert("huajiface.insertUrl", param);
					urlqueue.addQueue(href);
				}
				session.commit();
				map.clear();
			}
		}
		Elements ps = content.select(".p1 > .p_left > a");
		for (Element p : ps) { 
			log.debug(p.toString());
			String link = p.attr("href");
			Elements pps = p.getElementsByTag("p");
			String title = pps.get(0).text();
			String article = pps.get(1).html();
			Map<String, String> parameter = new HashMap<>();
			parameter.put("URL", link);
			parameter.put("TITLE", title);
			parameter.put("TEXT", article);
			session.insert("huajiface.insertcontent", parameter);
			System.out.println(link+"|"+title+"|"+article);
			session.commit();
		}
		return null;
	}

	
	public SqlSession getSqlSession(){
		try{
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("select_config.xml"));
			return factory.openSession();
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	/*public static void main(String[] args) {
		new SimpleDoucumentHandler();
	}*/
	
	
}
