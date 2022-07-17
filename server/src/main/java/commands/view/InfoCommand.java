package commands.view;


import commands.AbstractCommand;
import dto.UserDTO;
import interaction.ArgumentHolder;
import interaction.Request;
import interaction.Response;
import commands.exceptions.EmptyFieldCommandException;
import interaction.ResponseBody;
import manager.CommandManager;
import service.ProductService;
import service.UserService;

/**
 * Выводит в стандартный поток вывода информацию о коллекции.
 */

public class InfoCommand extends AbstractCommand {
    private ProductService productService;
    private UserService userService;
    public InfoCommand(ProductService productService,UserService userService) {
        super("info","output collection information"  + " to the standard output stream");
        this.productService =productService;
        this.userService=userService;
    }

    @Override
    public Response execute(ArgumentHolder argumentHolder, UserDTO userDTO) {
        ResponseBody responseBody = new ResponseBody();
        UserDTO user = userService.findByLogin(userDTO.getLogin()).get();
        responseBody.addCommandResponseBody(productService.info(user.getId()));
        return new Response(responseBody);
    }
}
