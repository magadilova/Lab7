package commands;


import client.Client;
import interaction.Request;
import interaction.Response;
import interaction.ResponseCode;
import workers.ConsoleWorker;

/**
 * Завершает работу программы.
 */
public class ExitCommand extends AbstractCommand {


    public ExitCommand() {
        super("exit", "", "exit program without saving collection into file");
    }

    @Override
    public void execute(String arguments) {
        if (arguments == null) {
            Request request = new Request(getName(), Client.userDTO);
            Client.sendRequest(request);
            Response response = Client.getResponse();
            if (response.getResponseCode()== ResponseCode.DISABLE){
                System.exit(0);
            }
        } else {
            ConsoleWorker.println("This command does not contain any additional parameters ");
        }
    }
}
