package javaclass.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String path = "RandomAccessFile.txt";
		String str = "hello world one!\r\n";
		Boolean mode = false;
		RandomAccessFileTest.writeContent(path, str, mode);
		
		str = RandomAccessFileTest.readContent(path);
		System.out.println("文件内容：" + str);
	}
	
	
	/**
	 * 从文件中读取内容
	 * 
	 * @param path
	 * @return
	 */
	public static String readContent(String path)
	{
		String content = "";
		try {
			String str = null;
			RandomAccessFile raf = new RandomAccessFile(path, "rw");
			str = raf.readLine();
			while(str != null)
			{
				content += str + "\n";
				str = raf.readLine();
			}
			raf.close();
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
	 * 向文件中写内容
	 * 
	 * @param path
	 * @param str
	 * @param mode		if mode is true, the string will be added at the end of the file,
	 * 					if not, the string will overwrite the file;
	 */
	public static void writeContent(String path,String str, Boolean mode)
	{
		try {
			RandomAccessFile raf = new RandomAccessFile(path, "rw");
			long length = 0;
			if(mode == false)
			{
				raf.setLength(0);
			}
			length = raf.length();
			raf.seek(length);
			raf.write(str.getBytes());
			raf.close();
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
