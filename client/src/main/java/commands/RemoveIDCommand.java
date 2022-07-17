package commands;


import client.Client;
import interaction.ArgumentHolder;
import interaction.Request;
import workers.ConsoleWorker;

import java.io.IOException;

/**
 * Удаляет элемент из коллекции по его id.
 */

public class RemoveIDCommand extends AbstractCommand {

    public RemoveIDCommand() {
        super("remove_by_id","id" ,"remove element from collection by its id.");
    }

    @Override
    public void execute(String arguments) {
        try {
            long id = Long.parseLong(arguments);
            ArgumentHolder argumentHolder = new ArgumentHolder(id);
            Request request = new Request(getName(), argumentHolder, Client.userDTO);
            Client.sendRequest(request);
            result();
        }catch (NumberFormatException e){
            ConsoleWorker.println("The id should be represented by a number");
        }

    }
}
