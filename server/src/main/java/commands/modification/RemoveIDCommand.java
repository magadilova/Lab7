package commands.modification;

import commands.AbstractCommand;
import dto.UserDTO;
import interaction.ArgumentHolder;
import interaction.Response;
import interaction.ResponseBody;
import service.ProductService;
import service.UserService;

/**
 * Удаляет элемент из коллекции по его id.
 */

public class RemoveIDCommand extends AbstractCommand {
    private final UserService userService;
    private final ProductService productService;

    public RemoveIDCommand(ProductService productService, UserService userService) {
        super("remove_by_id","remove element from collection by its id");
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public Response execute(ArgumentHolder argumentHolder, UserDTO userDTO) {
        ResponseBody responseBody = new ResponseBody();
        UserDTO user = userService.findByLogin(userDTO.getLogin()).get();
        if (productService.removeById(user.getId(), argumentHolder.getId())) {
            responseBody.addCommandResponseBody("The product has been successfully removed");
        } else {
            responseBody.addCommandResponseBody("You cannot delete an item with this id");
        }
        return new Response(responseBody);
    }
}
