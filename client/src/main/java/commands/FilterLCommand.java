package commands;


import client.Client;
import interaction.ArgumentHolder;
import interaction.Request;
import workers.ConsoleWorker;

/**
 * выводит элементы, значение поля price которых меньше заданного
 */

public class FilterLCommand extends AbstractCommand {

    public FilterLCommand() {
        super("filter_less_than_price", "price", "output the elements whose value of the price " +
                "field is less than the given value");
    }

    @Override
    public void execute(String arguments) {
        try {
            double price = Double.parseDouble(arguments);
            ArgumentHolder argumentHolder = new ArgumentHolder(price);
            Request request = new Request(getName(), argumentHolder, Client.userDTO);
            Client.sendRequest(request);
            result();
        } catch (NumberFormatException e) {
            ConsoleWorker.println("The price should be represented by a number");
        }
    }
}
