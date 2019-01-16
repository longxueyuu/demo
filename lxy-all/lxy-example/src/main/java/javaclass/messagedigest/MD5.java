package javaclass.messagedigest;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;

public class MD5 {

	public static void main(String[] args) throws Exception {
		
		System.out.println(MD5.getProviderName());
		
		MessageDigest md = MessageDigest.getInstance("MD5");
		
		String path = "D:\\Download\\";
		String name = "Once.Upon.a.Time.in.America.1984.美国往事.双语字幕.HR-HDTV.AC3.1024X576.x264-人人影视制作V2.mkv";
		
		RandomAccessFile raf = new RandomAccessFile("channel.txt", "r");
		FileChannel fc = raf.getChannel();
		ByteBuffer buf = ByteBuffer.allocate(1024 * 1024);
		int bytes = -1;
		while(-1 != (bytes = fc.read(buf)))
		{
			buf.flip();
			while(buf.hasRemaining())
			{
				md.update(buf);
			}
			buf.clear();
		}
		
		byte[] message = md.digest();
		StringBuilder sb = new StringBuilder();
		System.out.println("----- " + Integer.toHexString(message[0]));
		for(byte i : message)
		{
			System.out.println(i);
			sb.append(Integer.toHexString(i));
			System.out.println("****  " + Integer.toHexString(i));
		}
		System.out.println("hashvalue: " + sb.toString());
		fc.close();
		raf.close();
		String result = MD5.toHex(message);
		System.out.println(result + "\n\n");
		
		// test big-endian
		int num = -889275714;
		System.out.println(Integer.toHexString(num));
		long num2 = 0xcafebabeL;
		System.out.println(num2);
		
		System.out.println(Integer.toHexString(num & 0xff));
		System.out.println(Integer.toHexString(num & 0xff00));
		System.out.println(Integer.toHexString(num & 0xff0000));
		System.out.println(Integer.toHexString(num & 0xff000000));
		
	}
	
	/**
	 * 
	 * @param digest
	 * @return
	 */
	private static String toHex(byte[] digest) 
	{ 

	 StringBuffer buf=new StringBuffer(); 
	 for(int i=0;i<digest.length;i++) 
	 { 
		 String temp = Integer.toHexString((int)digest[i]&0x00ff); 
		 System.out.println("temp : " + temp);
		 if(temp.length() < 2) 
		 { 
			 buf.append("0"); 
		 } 
		 buf.append(temp); 
	 } 

	 return buf.toString(); 
	} 
	
	public static List<String> getProviderName()
	{
		List<String> list = new ArrayList<String>();
		Provider[] providers = Security.getProviders();
		for(Provider p : providers)
		{
			list.add(p.getName());
		}
		return list;
	}
	
}














