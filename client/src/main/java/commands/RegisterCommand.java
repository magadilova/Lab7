package commands;

import client.Client;
import interaction.Request;
import workers.Asker;
import workers.ConsoleWorker;


public class RegisterCommand extends AbstractCommand{
    public RegisterCommand() {
        super("register", "", "");
    }

    @Override
    public void execute(String arguments) {
        if (arguments==null){
            Asker asker = new Asker(System.console());
            Client.sendRequest(new Request(getName(),asker.makeUserDTO()));
            result();
        }
    }
}
