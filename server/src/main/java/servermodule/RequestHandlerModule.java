package servermodule;


import interaction.Request;
import interaction.Response;
import manager.CommandManager;

import java.nio.channels.SocketChannel;
import java.util.AbstractMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

public class RequestHandlerModule implements Runnable {
    public static ConcurrentLinkedQueue<Map.Entry<SocketChannel, Response>> responseQueue = new ConcurrentLinkedQueue<>();
    private final CommandManager commandManager;
    private final Map.Entry<SocketChannel, Request> requestEntry;


    public RequestHandlerModule(CommandManager commandManager, Map.Entry<SocketChannel, Request> requestEntry) {
        this.commandManager = commandManager;
        this.requestEntry = requestEntry;
    }

    @Override
    public void run() {
        Request value = requestEntry.getValue();
        Response response = commandManager.executeCommand(value.getCommandName(), value.getArgumentHolder(), value.getUserDTO());
        responseQueue.add(new AbstractMap.SimpleEntry<>(requestEntry.getKey(), response));
    }
}
