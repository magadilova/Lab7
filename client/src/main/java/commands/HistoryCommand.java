package commands;

import client.Client;
import interaction.Request;
import workers.ConsoleWorker;

/**
 * Выводит последние 12 команд
 */

public class HistoryCommand extends AbstractCommand {

    public HistoryCommand() {
        super("history", "", "print the last 12 commands");
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
