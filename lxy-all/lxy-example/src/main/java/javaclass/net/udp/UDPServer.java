package javaclass.net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPServer {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		DatagramSocket socket = new DatagramSocket();
		String str = "Hello World!";
		DatagramPacket packet = new DatagramPacket(str.getBytes(), str.length(), InetAddress.getByName("localhost"), 7000);
		socket.send(packet);
		
		byte[] buffer = new byte[1000];
		DatagramPacket packet2 = new DatagramPacket(buffer, 1000);
		socket.receive(packet2);
		System.out.println(new String(buffer, 0, packet2.getLength()));
		socket.close();
	}

}
