package commands;

import client.Client;
import interaction.Response;
import workers.ConsoleWorker;

/**
 * Абстрактный класс комманд
 */

public abstract class AbstractCommand {
    private final String name;
    private final String description;
    private final String params;

    public AbstractCommand(String name, String params, String description) {
        this.name = name;
        this.params = params;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void result() {
        try {
            Response response = Client.getResponse();
            ConsoleWorker.println(response.getResponseBody().getData());
        }catch (Exception e){
            
        }

    }

    public String getDescription() {
        return description;
    }

    public String getParameters() {
        return params;
    }


    abstract public void execute(String arguments);
}
