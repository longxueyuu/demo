package javaclass.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;



public class FileChannelDemo {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		String inFile = "hs_err_pid5500.log";
		String outFile = "outFile.txt";
		FileInputStream fis = new FileInputStream(inFile);
		FileOutputStream fos = new FileOutputStream(outFile);
		
		FileChannel fin = fis.getChannel();
		FileChannel fout = fos.getChannel();
		
		ByteBuffer byteBuf = ByteBuffer.allocate(2048);
		
		while(true)
		{
			byteBuf.clear();
			int len = fin.read(byteBuf);
			if(-1 == len)
			{
				break;
			}
			byteBuf.flip();
			while(byteBuf.hasRemaining())
			{
				fout.write(byteBuf);
			}
		}
		
		fin.close();
		fout.close();
		fis.close();
		fos.close();
		
		
		
		
		String filePath = "channel.txt";
		FileInputStream fis2 = new FileInputStream(filePath);
		FileChannel fin2 = fis2.getChannel();
		ByteBuffer buf = ByteBuffer.allocate(64);
		int bytes = 0;
		while(-1 != (bytes = fin2.read(buf)))
		{
			buf.flip();
			while(buf.hasRemaining())
			{
				System.out.print((char)buf.get());
			}
			buf.clear();
		}

	}

}


















