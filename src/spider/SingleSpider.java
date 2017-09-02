package spider;

import java.util.List;
import org.apache.log4j.Logger;
import parser.Parser;
import fetcher.Fetcher;

public class SingleSpider implements Spider ,Runnable{
	
	private Logger log = Logger.getLogger(SingleSpider.class);
	
	private Fetcher<String> fetcher;
	
	private Parser paser;
	
	private List<String> seeds;

	public SingleSpider(Fetcher<String> fetcher,Parser paser,List<String> seeds) {
		this.fetcher = fetcher;
		this.paser = paser;
		this.seeds = seeds;
	}
	
	@Override
	public void run() {
		while(true){
			String url = null;
			synchronized (seeds) {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				while(seeds.size() == 0){
					try {
						seeds.notifyAll();
						seeds.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				url = seeds.get(0);
				seeds.remove(0);
			}
			try {
				String content = fetcher.getPageHtml(url);
				paser.setContent(content);
				log.debug("title:"+paser.evalTitle()+";url:"+url+";");
				List<String> href= paser.hrefs();
				synchronized (seeds) {
					seeds.addAll(href);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	
}
