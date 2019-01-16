package javaclass.net.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		ServerSocket ss = new ServerSocket(6000);
		while(true)
		{
			Socket socket = ss.accept();
			new ServerInputThread(socket).start();
			new ServerOutputThread(socket).start();
		}
		
		
//		InputStream is = socket.getInputStream();
//		
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
//		
//		
//		OutputStream os = socket.getOutputStream();
//		os.write("welcome!".getBytes());
//		is.close();
//		os.close();
//		socket.close();

	}

}
