package servermodule;

import interaction.Request;
import interaction.Response;
import manager.CommandManager;
import utils.ServiceManager;
import utils.exceptions.ReadingFilePropertiesException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.AbstractMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;


public class Server {
    private final int serverPort;
    private ServerSocketChannel serverSocketChannel;
    private CommandManager commandManager;
    private Selector selector;
    private ServiceManager serviceManager;
    private ThreadManager threadManager;

    public Server(int serverPort) {
        this.serverPort = serverPort;
    }

    public void start() throws NoSuchMethodException, ReadingFilePropertiesException, InstantiationException, IllegalAccessException, InvocationTargetException {
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(serverPort));
            serviceManager = new ServiceManager();
            commandManager = new CommandManager(serviceManager.getUserService(), serviceManager.getPersonService());
            selector = Selector.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            threadManager = new ThreadManager(
                    Executors.newCachedThreadPool(),
                    new ForkJoinPool(Runtime.getRuntime().availableProcessors()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        System.out.println("The server is running");
        while (true) {
            try {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();

                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isValid()) {
                        if (key.isAcceptable()) {
                            SocketChannel accepted = serverSocketChannel.accept();
                            accepted.configureBlocking(false);
                            accepted.register(key.selector(), SelectionKey.OP_READ);
                        }
                        if (key.isReadable()) {
                            SocketChannel socketChannel = (SocketChannel) key.channel();
                            threadManager.receiveRequest(new RequestReaderModule(socketChannel, selector));
                            checkReceivedRequest();
                            if (RequestReaderModule.requestQueue.size() > 0) {
                                Map.Entry<SocketChannel, Request> entry = RequestReaderModule.requestQueue.poll();
                                Thread thread = new Thread(new RequestHandlerModule(commandManager, new AbstractMap.SimpleEntry<>(entry.getKey(), entry.getValue())));
                                thread.start();
                            }
                        }
                        if (key.isWritable()){
                            if (RequestHandlerModule.responseQueue.size() > 0) {
                                Map.Entry<SocketChannel, Response> entry = RequestHandlerModule.responseQueue.poll();
                                threadManager.sendResponse(new ResponseSenderModule(new AbstractMap.SimpleEntry<>(entry.getKey(), entry.getValue()), selector, key));
                            }}
                        }
                        iterator.remove();
                    }


                } catch(IOException e){
                    e.printStackTrace();
                }
            }
        }







        private void checkReceivedRequest(){
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
