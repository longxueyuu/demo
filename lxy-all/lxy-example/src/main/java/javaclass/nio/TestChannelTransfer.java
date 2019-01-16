package javaclass.nio;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class TestChannelTransfer {

	public static void main(String[] args) throws Exception {
		
		
		RandomAccessFile src = new RandomAccessFile("src.txt", "rw");
		FileChannel srcChannel = src.getChannel();
		
		RandomAccessFile dst = new RandomAccessFile("dst.txt", "rw");
		FileChannel dstChannel = dst.getChannel();
		
		int position = 0;
		long count = srcChannel.size();
		dstChannel.transferFrom(srcChannel, 0, count);
		
		srcChannel.close();
		dstChannel.close();
		dstChannel.force(true);
		src.close();
		dst.close();
		
	}
	
}































