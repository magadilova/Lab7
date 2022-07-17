package commands;

import dto.UserDTO;
import interaction.ArgumentHolder;
import interaction.Request;
import interaction.Response;

/**
 * Абстрактный класс комманд
 */

public abstract class AbstractCommand {
    private String name;
    private String description;
    public AbstractCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    abstract public Response execute(ArgumentHolder argumentHolder, UserDTO userDTO);

    public String getName() {
        return name;
    }

    public String getDescription(){
        return description;
    }
}
