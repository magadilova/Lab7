package client;

import commands.*;
import dto.UserDTO;
import interaction.Request;
import interaction.Response;
import managers.ClientCommandManager;
import utils.Serializator;
import workers.ConsoleWorker;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;


public class Client {
    public static UserDTO userDTO;
    private static int port;
    private static String host;
    private static SocketChannel socketChannel;
    public static boolean authorized = false;
    private static Request requestShouldBeSent;

    public Client(String host, int port) {
        Client.port = port;
        Client.host = host;
    }

    public static void close() {
        try {
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void waitConnection() {
        int sec = 0;
        close();
        while (!socketChannel.socket().isConnected()) {
            try {
                socketChannel = SocketChannel.open(new InetSocketAddress(InetAddress.getByName(host), port));
                ConsoleWorker.println("Reconnection completed successfully. Continuation of execution.");
                return;
            } catch (IOException e) {
            }
            ConsoleWorker.println("\rConnection error. Waiting for reconnect: " + sec + "/60 seconds");
            sec++;
            if (sec > 60) {
                System.exit(0);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
            }
        }
    }

    public static void sendRequest(Request request) {
        requestShouldBeSent = request;
        byte[] bytes = Serializator.serializeObject(request);
        try {
            ByteBuffer buffer = ByteBuffer.wrap(bytes);
            socketChannel.write(buffer);
        } catch (Exception e) {
            waitConnection();
            sendRequest(request);
        }
    }

    public static Response getResponse() {
        try {
            ByteBuffer buffer = ByteBuffer.allocate(8192);
            //while (socketChannel.read(buffer) <= 0) {
                socketChannel.read(buffer);
                Response response = Serializator.deserializeObject(buffer.array());
                return response;
            //}
        } catch (IOException e) {
            waitConnection();
            sendRequest(requestShouldBeSent);
            return getResponse();
        }
       // return getResponse();
    }

    public boolean connect() {
        try {
            socketChannel = SocketChannel.open(new InetSocketAddress(host, port));

        } catch (IOException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public void run() {
        while (true) {
            get();
        }
    }

    public void get() {
        ClientCommandManager commandManager = new ClientCommandManager();
        commandManager.addCommands(new AbstractCommand[]{
                new HelpCommand(),
                new InfoCommand(),
                new AddCommand(commandManager),
                new UpdateCommand(commandManager),
                new ShowCommand(),
                new RemoveGCommand(commandManager),
                new RemoveIDCommand(),
                new ExecuteCommand(commandManager),
                new ExitCommand(),
                new ClearCommand(),
                new FilterLCommand(),
                new FilterPNCommand(),
                new HistoryCommand(),
                new RemoveLCommand(commandManager),
                new RemovePNCommand(),
                new LoginCommand(),
                new RegisterCommand()
        });
        while ((!authorized)) {
            ConsoleWorker.println("Enter command:");
            ConsoleWorker.println("command for authorization: login\n" +
                    "command for registration: register");
            String command = ClientCommandManager.console.readLine().trim();
            if (command.equals("login") || command.equals("register")) {
                commandManager.executeCommand(command);
            } else {
                ConsoleWorker.println("This command is not available");
            }
        }
        commandManager.startInteractiveMode();
    }

}
