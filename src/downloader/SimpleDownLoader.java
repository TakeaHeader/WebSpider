package downloader;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class SimpleDownLoader extends BaseDownloader{
	
	
	@Override
	public void DownloadUrl(String url,String location){
		InputStream in = GetInputStream(url);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(location);
			byte [] b = new byte[2048];
			int len = b.length;
			while ((len = in.read(b, 0, len)) != -1) {
				fos.write(b,0,len);
				fos.flush();
			}
		} catch (FileNotFoundException e) {
			log.error("The File is Not Found:"+location);
		} catch (IOException e) {
			log.error(e.getMessage());
		}finally{
			try {
				if(in != null){
					in.close();
				}
				if(fos != null){
					fos.close();
				}
			} catch (IOException e) {
				//ignore
			}
			
		}
	}

}
