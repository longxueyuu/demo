package javaclass.nio;

import java.io.IOException;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Stack;

public class TestSelectorClient {

	public static void main(String[] args) throws IOException {
		
		Selector selector = Selector.open();
		
		Socket socket = new Socket();
		SocketChannel socketChannel = socket.getChannel();
		socketChannel.configureBlocking(false);
		SelectionKey selectorKey = socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
		
		SocketChannel sc = (SocketChannel)selectorKey.channel();
		
		Stack stack;
		
	}
	
	
}
