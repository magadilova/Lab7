package commands;


import client.Client;
import interaction.Request;
import workers.ConsoleWorker;

/**
 * Выводит справку по доступным командам.
 */

public class HelpCommand extends AbstractCommand {


    public HelpCommand() {
        super("help", "", "display help for available commands");

    }

    @Override
    public void execute(String arguments) {
        if (arguments == null) {
            Request request = new Request(getName(), Client.userDTO);
            Client.sendRequest(request);
            result();
        } else {
            ConsoleWorker.println("This command does not contain any additional parameters ");
        }

    }
}
