package commands;


import client.Client;
import interaction.Request;
import workers.ConsoleWorker;

/**
 * Очищает коллекцию.
 */

public class ClearCommand extends AbstractCommand {


    public ClearCommand() {
        super("clear", "", "clears collection");
    }

    @Override
    public void execute(String argument) {
        if (argument == null) {
            Request request = new Request(getName(), Client.userDTO);
            Client.sendRequest(request);
            result();
        } else {
            ConsoleWorker.println("This command does not contain any additional parameters ");
        }

    }
}
