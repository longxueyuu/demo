package javaclass.nio;

import com.rabbitmq.client.AMQP;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.Set;

public class NioServer {

    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private SelectionKey selectionKey;
    ByteBuffer buf = ByteBuffer.allocate(1028);

    public NioServer() throws IOException {
        selector = SelectorProvider.provider().openSelector();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9999));
        serverSocketChannel.configureBlocking(false);

        selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);


    }

    public static void main(String[] args) throws IOException {
        NioServer nioServer = new NioServer();
        nioServer.select();


    }

    public void select() {
        while (true) {
            try {
                /**
                 * 如果客户端调用socket.close()关闭，select()会一直触发SelectionKey.OP_READ事件，
                 * 但是服务端socketchannel.read(buf)的返回值为-1，也有可能是0，
                 * 所以需要根据返回值进行识别，并关闭socket
                  */
                selector.select();
                Iterator<SelectionKey> keysIterator = selector.selectedKeys().iterator();
                while (keysIterator.hasNext()) {
                    SelectionKey key = keysIterator.next();
                    int ops = key.interestOps();
                    if ((ops & SelectionKey.OP_ACCEPT) != 0) {
                        ServerSocketChannel ch = (ServerSocketChannel) key.channel();
                        SocketChannel socketChannel = ch.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    } else if ((ops & SelectionKey.OP_READ) != 0) {
                        SocketChannel ch = (SocketChannel) key.channel();
                        int size = ch.read(buf);
                        buf.flip();
                        int value = buf.getInt();
                        System.out.println(value);
                    }

                    buf.clear();
                    keysIterator.remove();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
