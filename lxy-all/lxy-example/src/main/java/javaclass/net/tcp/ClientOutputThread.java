package javaclass.net.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ClientOutputThread extends Thread {

	private Socket socket;
	ClientOutputThread(Socket socket)
	{
		this.socket = socket;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			OutputStream os = socket.getOutputStream();
			while(true)
			{
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				String line = br.readLine();
				os.write(line.getBytes());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
