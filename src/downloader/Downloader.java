package downloader;

import java.util.Properties;

/**
 * @author WJ
 * Downloader  下载接口,用于网络请求下载
 */
public interface Downloader {
	
	/**
	 * @param prop  初始化参数
	 */
	public void setProperties(Properties prop);
	
	
	/**
	 * @param url  下载地址
	 * @param location  存储位置
	 */
	public void DownloadUrl(String url,String location);

  
}
