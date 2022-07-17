package commands.view;

import commands.AbstractCommand;
import dto.UserDTO;
import interaction.ArgumentHolder;
import interaction.Response;
import interaction.ResponseBody;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Выводит справку по доступным командам.
 */

public class HelpCommand extends AbstractCommand {
    private HashMap<String, String> helpText;

    public HelpCommand() {
        super("help","display help for available commands");
    }

    @Override
    public Response execute(ArgumentHolder argumentHolder, UserDTO userDTO) {
        ResponseBody responseBody = new ResponseBody();
        List<String> tempList = new LinkedList<>();
        helpText.forEach((key,value)->tempList.add(key + ": " + value));
        responseBody.addCommandResponseBody(String.join("\n",  tempList));
        return new Response(responseBody);
    }

    public void setHelpText(HashMap<String, String> helpText) {
        this.helpText = helpText;
    }
}
