package commands.view;

import commands.AbstractCommand;
import dto.UserDTO;
import interaction.ArgumentHolder;
import interaction.Request;
import interaction.Response;
import commands.exceptions.EmptyFieldCommandException;
import interaction.ResponseBody;
import manager.CommandManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Выводит последние 12 команд
 */

public class HistoryCommand extends AbstractCommand {
    private ConcurrentHashMap<UserDTO, List<String>> historyMap = new ConcurrentHashMap<>();

    public HistoryCommand() {
        super("history","print the last 12 commands");
    }

    public void addToCommandList(UserDTO userDTO, String commandName) {
        // TODO проверить потом
        if (!commandName.equals("login") || !commandName.equals("register")){
            if (historyMap.containsKey(userDTO)){
                List<String> historyList = historyMap.get(userDTO);
                historyList.add(commandName);
                historyMap.put(userDTO, historyList);
            }else {
                ArrayList<String> historyList = new ArrayList<>();
                historyList.add(commandName);
                historyMap.put(userDTO, historyList);
            }
        }

    }

    @Override
    public Response execute(ArgumentHolder argumentHolder, UserDTO userDTO) {
        ResponseBody responseBody = new ResponseBody();
        responseBody.addCommandResponseBody(String.join("\n", historyMap.get(userDTO)));
        return new Response(responseBody);
    }
}

