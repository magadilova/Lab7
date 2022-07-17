package commands;


import managers.ClientCommandManager;

import javax.script.ScriptException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ExecuteCommand extends AbstractCommand {
    ClientCommandManager commandManager;

    public ExecuteCommand(ClientCommandManager commandManager) {
        super("execute_script", "file_name", "read and execute script from file setup.");
        this.commandManager = commandManager;
    }


    @Override
    public void execute(String arguments) {
        ClientCommandManager.setFileMode(true);
        try {
            File file = new File(arguments);
            if (arguments.isEmpty()) {
                throw new IllegalArgumentException();
            }
            if (commandManager.getFiles().contains(file.getAbsolutePath()))
                throw new ScriptException("Scripts can't be recursive!!!");
            else {
                if (!file.exists()) {
                    throw new FileNotFoundException("File not found. x_X ");
                }

                commandManager.getScanners().add(new Scanner(file));
                commandManager.getFiles().add(file.getAbsolutePath());
            }
        } catch (IllegalArgumentException | FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (ScriptException e) {
            System.err.println(e.getMessage());
            commandManager.startInteractiveMode();
        }
        while (commandManager.getScanners().getLast().hasNextLine()) {
            String command = commandManager.getScanners().getLast().nextLine();
            commandManager.executeCommand(command);
        }
        commandManager.getScanners().removeLast();
        commandManager.getFiles().removeLast();
        ClientCommandManager.setFileMode(false);

    }
}
