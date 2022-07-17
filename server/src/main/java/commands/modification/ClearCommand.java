package commands.modification;

import commands.AbstractCommand;
import dto.UserDTO;
import interaction.ArgumentHolder;
import interaction.Response;
import interaction.ResponseBody;
import service.ProductService;
import service.UserService;

import java.util.Optional;

/**
 * Очищает коллекцию.
 */

public class ClearCommand extends AbstractCommand {
    private final UserService userService;
    private final ProductService productService;

    public ClearCommand(ProductService productService, UserService userService) {
        super("clear","clears collection");
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public Response execute(ArgumentHolder argumentHolder, UserDTO userDTO) {
        ResponseBody responseBody = new ResponseBody();
        Optional<UserDTO> byLogin = userService.findByLogin(userDTO.getLogin());
        long countDeleted =0;
        if (byLogin.isPresent()){
            countDeleted = productService.clear(byLogin.get().getId());
        }
        if (countDeleted != 0) {
            responseBody.addCommandResponseBody(String.format("The count of deleted items created by you = %d", countDeleted));
        } else {
            responseBody.addCommandResponseBody("You can only delete items that you have created yourself. At the moment you have not created a single element");
        }
        return new Response(responseBody);

    }
}
