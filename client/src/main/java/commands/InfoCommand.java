package commands;


import client.Client;
import interaction.Request;
import workers.ConsoleWorker;

/**
 * Выводит в стандартный поток вывода информацию о коллекции.
 */

public class InfoCommand extends AbstractCommand {

    public InfoCommand() {
        super("info", "", "output collection information"  + " to the standard output stream");
    }

    @Override
    public void execute(String arguments) {
        if (arguments == null) {
            Request request = new Request(getName(), Client.userDTO);
            Client.sendRequest(request);            // Отправление запроса
            result();
        } else {
            ConsoleWorker.println("This command does not contain any additional parameters ");
        }

    }
}
