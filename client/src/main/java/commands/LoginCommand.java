package commands;

import client.Client;
import dto.UserDTO;
import interaction.Request;
import interaction.Response;
import interaction.ResponseCode;
import workers.Asker;
import workers.ConsoleWorker;


public class LoginCommand extends AbstractCommand {
    public LoginCommand() {
        super("login", "", "");
    }

    @Override
    public void execute(String arguments) {
        if (arguments == null) {
            Asker asker = new Asker(System.console());
            UserDTO userDTO = asker.makeUserDTO();
            Client.sendRequest(new Request(getName(), userDTO));
            Response response = Client.getResponse();
            if (response.getResponseCode() == ResponseCode.AUTHORIZED) {
                Client.userDTO = userDTO;
                ConsoleWorker.println(response.getResponseBody().getData());
                Client.authorized = true;
            }else{
                ConsoleWorker.println(response.getResponseBody().getData());
            }
        }
    }
}
