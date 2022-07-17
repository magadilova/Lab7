package commands.modification;

import commands.AbstractCommand;
import dto.UserDTO;
import interaction.ArgumentHolder;
import interaction.Response;
import interaction.ResponseBody;
import service.ProductService;
import service.UserService;

/**
 * Удалены вес элементы, меньшие, чем заданный
 */
public class RemoveLCommand extends AbstractCommand {
    private final UserService userService;
    private final ProductService productService;

    public RemoveLCommand(ProductService productService, UserService userService) {
        super("filter_less_than_price","remove all items from the collection " +
                "that are smaller than the specified");
        this.userService = userService;
        this.productService = productService;
    }


    @Override
    public Response execute(ArgumentHolder argumentHolder, UserDTO userDTO) {
        ResponseBody responseBody = new ResponseBody();
        UserDTO user = userService.findByLogin(userDTO.getLogin()).get();
        long countDeleted = productService.removeLower(user.getId(), argumentHolder.getPrice());
        responseBody.addCommandResponseBody(String.format("The count of deleted items created by you = %d", countDeleted));
        return new Response(responseBody);
    }
}
