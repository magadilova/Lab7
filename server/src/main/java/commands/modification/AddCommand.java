package commands.modification;

import commands.AbstractCommand;
import dto.ProductDTO;
import dto.UserDTO;
import interaction.ArgumentHolder;
import interaction.Response;
import interaction.ResponseBody;
import service.ProductService;
import service.UserService;

/**
 * Добавляет в коллекцию новый элемент.
 */

public class AddCommand extends AbstractCommand {
    private final UserService userService;
    private final ProductService productService;

    public AddCommand(ProductService productService, UserService userService) {
        super("add", "add a new element to the collection");
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public Response execute(ArgumentHolder argumentHolder, UserDTO userDTO) {
        ResponseBody responseBody = new ResponseBody();
        ProductDTO productDTO = argumentHolder.getProductDTO();
        UserDTO realUser = userService.findByLogin(userDTO.getLogin()).get();
        productDTO.setUser(realUser);
        productService.create(productDTO);
        responseBody.addCommandResponseBody("The element was successfully added!");
        return new Response(responseBody);
    }
}

