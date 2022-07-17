package commands;


import client.Client;
import interaction.ArgumentHolder;
import interaction.Request;
import workers.ConsoleWorker;

/**
 * Выводит элементы, значение поля partNumber которых начинается с заданной подстроки
 */

public class FilterPNCommand extends AbstractCommand {

    public FilterPNCommand() {
        super("filter_starts_with_part_number", "partNumber", "output the elements whose " +
                "partNumber field value starts with the given substring");
    }

    @Override
    public void execute(String arguments) {
            if (arguments!=null){
                ArgumentHolder argumentHolder = new ArgumentHolder(arguments);
                Request request = new Request(getName(), argumentHolder, Client.userDTO);
                Client.sendRequest(request);
                result();
            }else{
                ConsoleWorker.println("This command does not contain any additional parameters ");
            }

    }
}
