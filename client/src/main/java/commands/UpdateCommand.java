package commands;


import client.Client;
import commands.exception.EmptyFieldCommandException;
import dto.ProductDTO;
import interaction.ArgumentHolder;
import interaction.Request;
import managers.ClientCommandManager;
import workers.Asker;
import workers.ConsoleWorker;

public class UpdateCommand extends AbstractCommand {
    ClientCommandManager commandManager;

    public UpdateCommand(ClientCommandManager commandManager) {
        super("update_id", "id {element}", "update the value of the collection item " +
                "whose id is the same as the given one");
        this.commandManager = commandManager;
    }


    @Override
    public void execute(String arguments) {
        try {
            if (arguments != null) {
                long id = Long.parseLong(arguments);
                Asker asker;
                if (ClientCommandManager.fileMode) {
                    asker = new Asker(commandManager.getScanners().getLast());
                } else {
                    asker = new Asker(ClientCommandManager.console);
                }
                ProductDTO dto = asker.makeDto();
                dto.setId(id);
                ArgumentHolder argumentHolder = new ArgumentHolder(dto);
                Request request = new Request(getName(), argumentHolder, Client.userDTO);
                Client.sendRequest(request);
                result();
            } else
                throw new EmptyFieldCommandException("Exception: This command needs the value \" id \"");
        } catch (EmptyFieldCommandException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            ConsoleWorker.println("The price should be represented by a number");
        }
    }
}
