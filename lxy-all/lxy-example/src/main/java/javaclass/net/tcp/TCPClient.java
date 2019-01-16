package javaclass.net.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub

		Socket socket = new Socket("127.0.0.1", 6000);
		new ClientInputThread(socket).start();
		new ClientOutputThread(socket).start();
		
//		OutputStream os = socket.getOutputStream();
//		os.write("hello world!".getBytes());
//		
//		
//		InputStream is = socket.getInputStream();
//		byte[] buffer = new byte[2048];
//		int length = 0;
//		length = is.read(buffer, 0, buffer.length);
//		String resp = new String(buffer,0,length);
//		System.out.println(resp);
////		while(-1 != (length = is.read(buffer, 0, buffer.length)))
////		{
////			String resp = new String(buffer,0,length);
////			System.out.println(resp);
////		}
//		os.close();
//		is.close();
//		socket.close();
		
	}

}
