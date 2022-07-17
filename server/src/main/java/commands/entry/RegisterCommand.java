package commands.entry;

import commands.AbstractCommand;
import dto.UserDTO;
import encryption.exceptions.PasswordEncryptionException;
import interaction.ArgumentHolder;
import interaction.Response;
import interaction.ResponseBody;
import service.UserService;


public class RegisterCommand extends AbstractCommand {
    private UserService userService;
    public RegisterCommand(UserService userService) {
        super("register","");
        this.userService = userService;
    }

    @Override
    public Response execute(ArgumentHolder argumentHolder, UserDTO userDTO) {
        ResponseBody responseBody = new ResponseBody();

        try {
            if(userService.create(userDTO)){
                responseBody.addCommandResponseBody("You have successfully registered");
            }else {
                responseBody.addCommandResponseBody("User with such login data already exists");
            }
        } catch (PasswordEncryptionException e) {
            responseBody.addCommandResponseBody(e.getMessage());
        }
        return new Response(responseBody);
    }
}
