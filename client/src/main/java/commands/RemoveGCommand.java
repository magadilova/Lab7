package commands;

import client.Client;
import commands.exception.EmptyFieldCommandException;
import dto.ProductDTO;
import interaction.ArgumentHolder;
import interaction.Request;
import managers.ClientCommandManager;
import workers.Asker;

import java.io.IOException;

/**
 * Удаляет  из коллекции все элементы, превышающие заданный
 */

public class RemoveGCommand extends AbstractCommand {
    ClientCommandManager commandManager;

    public RemoveGCommand(ClientCommandManager commandManager) {
        super("remove_greater", "{element}", "remove all items from the collection that exceed the specified");
        this.commandManager = commandManager;
    }

    @Override
    public void execute(String arguments) {
        try {
            if (arguments == null) {
                Asker asker;
                if (ClientCommandManager.fileMode) {
                    asker = new Asker(commandManager.getScanners().getLast());
                } else {
                    asker = new Asker(ClientCommandManager.console);
                }
                ProductDTO dto = asker.makeDto();
                ArgumentHolder argumentHolder = new ArgumentHolder(dto);
                Request request = new Request(getName(),argumentHolder, Client.userDTO);
                Client.sendRequest(request);
                result();
            } else
                throw new EmptyFieldCommandException("Exception: This command must not have any characters");
        } catch (EmptyFieldCommandException e) {
            System.out.println(e.getMessage());
        }
    }
}
