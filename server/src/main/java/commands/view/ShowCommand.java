package commands.view;


import commands.AbstractCommand;
import dto.ProductDTO;
import dto.UserDTO;
import interaction.ArgumentHolder;
import interaction.Request;
import interaction.Response;
import commands.exceptions.EmptyFieldCommandException;
import interaction.ResponseBody;
import manager.CommandManager;
import model.Product;
import service.ProductService;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Выводит в стандартный поток вывода все элементы коллекции в строковом представлении.
 */

public class ShowCommand extends AbstractCommand {
    private ProductService productService;

    public ShowCommand(ProductService productService) {
        super("show","output to the standard output stream all the elements" +
                " of the collection in a string representation");
        this.productService = productService;
    }

    @Override
    public Response execute(ArgumentHolder argumentHolder, UserDTO userDTO) {
        ResponseBody responseBody = new ResponseBody();
        List<String> tempList  = new ArrayList<>();
        List<ProductDTO> all = productService.findAll();
        if (all.isEmpty()){
            responseBody.addCommandResponseBody("Count of elements : 0");
        }
        all.forEach(productDTO->tempList.add(productDTO.toString()));
        responseBody.addCommandResponseBody(String.join("\n", tempList));
        return new Response(responseBody);
    }
}
