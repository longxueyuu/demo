package javaclass.net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPClient {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		DatagramSocket socket = new DatagramSocket(7000);
		byte[] buffer = new byte[1000];
		DatagramPacket packetReceive= new DatagramPacket(buffer, 1000);
		socket.receive(packetReceive);
		System.out.println(new String(buffer, 0, packetReceive.getLength()));
		
		String str = "Welcome!";
		DatagramPacket packetSend = new DatagramPacket(str.getBytes(), str.length(), packetReceive.getAddress(), packetReceive.getPort());
		socket.send(packetSend);
		
	}

}
