package javaclass.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class InputOutputStream {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String path = "InputOutputStream.txt";
		String str = "how are you?\r\nfine,thank you\r\nAnd you?\r\n";
		Boolean mode = false;
		InputOutputStream.writeContent(path, str, mode);
		str = InputOutputStream.readContent(path);
		System.out.println("文件内容：" + str);
	}

	/**
	 * 读取文件内容
	 * 
	 * @param path
	 * @return
	 */
	public static String readContent(String path)
	{
		String content = "";
		
		try {
			FileInputStream fis = new FileInputStream(path);
			BufferedInputStream bis = new BufferedInputStream(fis);
			byte[] buffer = new byte[4096];
			int length = 0;
			while(-1 != (length = bis.read(buffer, 0, buffer.length)))
			{
				content += new String(buffer,0,length) + "\n";
			}
			bis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
	}
	
	/**
	 * 
	 * 
	 * @param path
	 * @param str
	 * @param mode		if mode is true, the string will be added at the end of the file,
	 * 					if not, the string will overwrite the file;
	 */
	public static void writeContent(String path, String str, Boolean mode)
	{
		try {
			FileOutputStream fos = new FileOutputStream(path,mode);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			bos.write(str.getBytes());
			bos.close();
			
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
