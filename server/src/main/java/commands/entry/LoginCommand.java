package commands.entry;

import commands.AbstractCommand;
import dto.UserDTO;
import encryption.exceptions.PasswordEncryptionException;
import interaction.ArgumentHolder;
import interaction.Response;
import interaction.ResponseBody;
import interaction.ResponseCode;
import service.UserService;


public class LoginCommand extends AbstractCommand {
    private UserService userService;

    public LoginCommand(UserService userService) {
        super("login", "");
        this.userService = userService;
    }

    @Override
    public Response execute(ArgumentHolder argumentHolder, UserDTO userDTO) {
        ResponseBody responseBody = new ResponseBody();
        try {
            if (userService.exists(userDTO)){
                responseBody.addCommandResponseBody("you have successfully logged in");
                return new Response(responseBody, ResponseCode.AUTHORIZED);
            }else {
                responseBody.addCommandResponseBody("A user with this username and password is not registered");
                return new Response(responseBody);
            }
        } catch (PasswordEncryptionException e) {
            responseBody.addCommandResponseBody(e.getMessage());
            return new Response(responseBody);
        }
    }
}
