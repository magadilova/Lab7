package commands.modification;


import commands.AbstractCommand;
import dto.ProductDTO;
import dto.UserDTO;
import interaction.ArgumentHolder;
import interaction.Response;
import interaction.ResponseBody;
import service.ProductService;
import service.UserService;
import service.exceptions.PermissionDeniedModificationException;

public class UpdateCommand extends AbstractCommand {
    private final UserService userService;
    private final ProductService productService;


    public UpdateCommand(ProductService productService, UserService userService) {
        super("update_id","update the value of the collection item " +
                "whose id is the same as the given one");
        this.userService = userService;
        this.productService = productService;
    }


    @Override
    public Response execute(ArgumentHolder argumentHolder, UserDTO userDTO) {
        ResponseBody responseBody = new ResponseBody();
        UserDTO user = userService.findByLogin(userDTO.getLogin()).get();
        ProductDTO productDTO = argumentHolder.getProductDTO();
        try {
            if (productService.update(user.getId(), productDTO)) {
                responseBody.addCommandResponseBody("There is no element with this id");
            } else {
                responseBody.addCommandResponseBody("there is no element with this id");
            }
        } catch (PermissionDeniedModificationException e) {
            responseBody.addCommandResponseBody(e.getMessage());
        }
        return new Response(responseBody);
    }
}
