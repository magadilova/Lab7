package commands;


import dto.UserDTO;
import interaction.*;

/**
 *  Завершает работу программы.
 */
public class ExitCommand extends AbstractCommand {

    public ExitCommand() {
        super("exit","exit program");
    }


    @Override
    public Response execute(ArgumentHolder argumentHolder, UserDTO userDTO) {
        ResponseBody responseBody = new ResponseBody();
        responseBody.addCommandResponseBody("Shut down");
        System.out.println(String.format("The client with login %s disconnected", userDTO.getLogin()));
        return new Response(responseBody, ResponseCode.DISABLE);
    }
}
