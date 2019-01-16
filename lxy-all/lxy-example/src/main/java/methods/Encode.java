package methods;

import java.io.IOException;
import java.security.MessageDigest;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
/**
 * 
 * @author lxy
 * @version 1.0, 04/20/2014
 * @since JDK1.6
 */
public class Encode {
	
	/**
	 * 
	 * @param password
	 * @return
	 */
	public static String MD5Encode(String password){
		String result = "";
		byte[] passwordbyte=password.getBytes(); 
		try 
		{ 
			MessageDigest md5=MessageDigest.getInstance("MD5");  
			md5.update(passwordbyte); 
			result = toHex(md5.digest()); 
		} 
		catch(Exception e) 
		{ 
			e.printStackTrace(); 
		} 
		return result;
		
	}
	
	/**
	 * 
	 * @param password
	 * @return
	 */
	public static String SHAEncode(String password){
		String result = "";
		byte[] passwordbyte=password.getBytes(); 
		try 
		{ 
			MessageDigest sha=MessageDigest.getInstance("SHA");  
			sha.update(passwordbyte); 
			result = toHex(sha.digest()); 
		} 
		catch(Exception e) 
		{ 
			e.printStackTrace(); 
		} 
		return result;
		
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
		 if(temp.length() < 2) 
		 { 
			 buf.append("0"); 
		 } 
		 buf.append(temp); 
	 } 

	 return buf.toString(); 
	} 
	
	public static String base64Encode(String str)
	{
		return new BASE64Encoder().encode(str.getBytes());
	}

	public static String base64Decode(String str) throws IOException
	{
		return new String(new BASE64Decoder().decodeBuffer(str));
	}
}
