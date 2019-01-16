package javaclass.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioClient {
    public static void main(String[] args) throws IOException {

        ByteBuffer buf = ByteBuffer.allocate(1024);
        SocketChannel socketChannel = SocketChannel.open();
        SocketAddress address = new InetSocketAddress(9999);
        socketChannel.connect(address);

        for(int i = 1; i <= 3; i++) {
            buf.putInt(i);
            buf.flip();
            socketChannel.write(buf);
            buf.clear();
        }
        /**
         * 如果客户端调用socket.close()关闭，服务端select()会一直触发SelectionKey.OP_READ事件
         */
        socketChannel.close();
        System.out.println("socket closed!");

    }

}
