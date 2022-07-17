package commands.modification;


import commands.AbstractCommand;
import dto.UserDTO;
import interaction.ArgumentHolder;
import interaction.Response;
import interaction.ResponseBody;
import service.ProductService;
import service.UserService;

/**
 * Удаляет элементы, значение поля partNumber которого эквивалентно заданному
 */

public class RemovePNCommand extends AbstractCommand {
    private final UserService userService;
    private final ProductService productService;

    public RemovePNCommand(ProductService productService, UserService userService) {
        super("remove_any_by_part_number","remove one element from the collection whose " +
                "partNumber field value is equivalent to the given");
        this.userService = userService;
        this.productService = productService;
    }


    @Override
    public Response execute(ArgumentHolder argumentHolder, UserDTO userDTO) {
        ResponseBody responseBody = new ResponseBody();
        UserDTO user = userService.findByLogin(userDTO.getLogin()).get();
        long countDeleted = productService.removePartNumber(user.getId(), argumentHolder.getPartNumber());
        responseBody.addCommandResponseBody(String.format("The count of deleted items created by you = %d", countDeleted));
        return new Response(responseBody);
    }
}
