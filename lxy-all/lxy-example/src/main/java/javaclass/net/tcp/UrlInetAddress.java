package javaclass.net.tcp;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class UrlInetAddress {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String src = "",line = null;
		URL url = new URL("http://www.china.com");
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
		FileWriter fw = new FileWriter("src.html");
		while(null != (line = br.readLine()))
		{
			src = src + line + "\n";
			fw.write(line);
		}
		br.close();
		fw.close();
		System.out.println("src:\n" + src);
	}

}
