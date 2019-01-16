package javaclass.net.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ClientInputThread extends Thread {

	private Socket socket;
	ClientInputThread(Socket socket)
	{
		this.socket = socket;
	}
	
	public void run(){
		// TODO Auto-generated method stub
		InputStream is;
		try {
			is = socket.getInputStream();
			while(true)
			{
				byte[] buffer = new byte[2048];
				int length = 0;
				length = is.read(buffer, 0, buffer.length);
				String resp = new String(buffer,0,length);
				System.out.println(resp);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
