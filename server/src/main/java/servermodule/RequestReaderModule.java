package servermodule;

import interaction.Request;
import utils.Serializator;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.AbstractMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;


public class RequestReaderModule implements Runnable {
    public static ConcurrentLinkedQueue<Map.Entry<SocketChannel, Request>> requestQueue  = new ConcurrentLinkedQueue<>();
    private final SocketChannel socketChannel;
    private final Selector selector;


    public RequestReaderModule(SocketChannel socketChannel, Selector selector) {
        this.socketChannel = socketChannel;
        this.selector = selector;
    }

    @Override
    public void run() {

        try {
            ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
            int read = socketChannel.read(byteBuffer);
            Request request = Serializator.deserializeObject(byteBuffer.array());
            System.out.println("A request was received " + request.getCommandName() + " from the client at " + socketChannel.getRemoteAddress());
            requestQueue.add(new AbstractMap.SimpleEntry<>(socketChannel, request));
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
