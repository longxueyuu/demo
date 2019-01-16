package javaclass.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReaderWriter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String path = "ReaderWriter.txt";
		String str = "hello\r\nworld\r\nwelcome\r\n";
		Boolean mode = false;
		ReaderWriter.writeContent(path, str, mode);
		str = ReaderWriter.readContent(path);
		System.out.println("文件内容:" + str);
	}
	
	/**
	 * 
	 * 
	 * @param path
	 * @return
	 */
	public static String readContent(String path)
	{
		String content = "";
		try {
			char[] buffer = new char[4096];
			FileReader fr = new FileReader(path);
			if(fr.ready())
			{
				int length;
				while(-1 != (length = fr.read(buffer, 0, buffer.length)))
				{
					content += new String(buffer,0,length) + "\n";
				}
			}
			fr.close();
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
	public static void writeContent(String path, String str, Boolean mode)
	{
		try {
			FileWriter fw = new FileWriter(path, mode);
			fw.write(str);
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
