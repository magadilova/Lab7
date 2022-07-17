package commands.modification;

import commands.AbstractCommand;
import dto.UserDTO;
import interaction.ArgumentHolder;
import interaction.Response;
import interaction.ResponseBody;
import service.ProductService;
import service.UserService;

/**
 * Удаляет  из коллекции все элементы, превышающие заданный
 */

public class RemoveGCommand extends AbstractCommand {
    private final UserService userService;
    private final ProductService productService;

    public RemoveGCommand(ProductService productService, UserService userService) {
        super("remove_greater", "remove all items from the collection that exceed the specified");
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public Response execute(ArgumentHolder argumentHolder, UserDTO userDTO) {
        ResponseBody responseBody = new ResponseBody();
        UserDTO user = userService.findByLogin(userDTO.getLogin()).get();
        long countDeleted = productService.removeGreater(user.getId(), argumentHolder.getPrice());
        responseBody.addCommandResponseBody(String.format("The count of deleted items created by you = %d", countDeleted));
        return new Response(responseBody);

    }
}
