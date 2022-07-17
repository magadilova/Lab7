package commands;


import client.Client;
import interaction.ArgumentHolder;
import interaction.Request;
import workers.ConsoleWorker;

/**
 * Удаляет элементы, значение поля partNumber которого эквивалентно заданному
 */

public class RemovePNCommand extends AbstractCommand {

    public RemovePNCommand() {
        super("remove_any_by_part_number", "partNumber", "remove one element from the collection whose " +
                "partNumber field value is equivalent to the given");
    }

    @Override
    public void execute(String arguments) {
        if (arguments != null) {
            Request request = new Request(getName(), new ArgumentHolder(arguments), Client.userDTO);
            Client.sendRequest(request);
            result();
        } else {
            ConsoleWorker.println("The command must contain parameters");
        }
    }
}
