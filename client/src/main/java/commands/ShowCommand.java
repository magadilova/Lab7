package commands;


import client.Client;
import interaction.Request;
import workers.ConsoleWorker;

/**
 * Выводит в стандартный поток вывода все элементы коллекции в строковом представлении.
 */

public class ShowCommand extends AbstractCommand {

    public ShowCommand() {
        super("show", "", "output to the standard output stream all the elements" +
                " of the collection in a string representation");
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
