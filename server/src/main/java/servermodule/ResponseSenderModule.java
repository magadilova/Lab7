package servermodule;


import interaction.Response;
import interaction.ResponseCode;
import utils.Serializator;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Map;

public class ResponseSenderModule implements Runnable {
    private final Map.Entry<SocketChannel, Response> responseEntry;
    private Selector selector;
    private SelectionKey selectionKey;
    public ResponseSenderModule(Map.Entry<SocketChannel, Response> responseEntry, Selector selector, SelectionKey selectionKey) {
        this.responseEntry = responseEntry;
        this.selector=selector;
        this.selectionKey = selectionKey;
    }

    @Override
    public void run() {
        byte[] bytes = Serializator.serializeObject(responseEntry.getValue());
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        SocketChannel socketChannel = responseEntry.getKey();
        try {
            socketChannel.write(byteBuffer);
            if (responseEntry.getValue().getResponseCode()==ResponseCode.DISABLE){
                selectionKey.cancel();
            }else{
                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_READ);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
