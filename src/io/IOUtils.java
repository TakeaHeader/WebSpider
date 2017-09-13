package io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class IOUtils {
	
	
	private IOUtils(){}
	
	/**
	 * 
	 * @param bt    写入的字节数组
	 * @param location 写入的位置
	 */
	public static void Download(byte [] bt,String location) {
		int size = bt.length;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(location);
		} catch (FileNotFoundException e) {
		}
		FileChannel fc = fos.getChannel();
		ByteBuffer by = ByteBuffer.wrap(bt,0,size);
		try {
			fc.write(by, 0);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(fos != null) {
				}
				if(by != null) {
					fc.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
