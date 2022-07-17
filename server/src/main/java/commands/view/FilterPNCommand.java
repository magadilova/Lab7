package commands.view;

import commands.AbstractCommand;
import dto.ProductDTO;
import dto.UserDTO;
import interaction.ArgumentHolder;
import interaction.Response;
import interaction.ResponseBody;
import service.ProductService;

import java.util.List;


/**
 * Выводит элементы, значение поля partNumber которых начинается с заданной подстроки
 */

public class FilterPNCommand extends AbstractCommand {
    private final ProductService productService;

    public FilterPNCommand(ProductService productService) {
        super("filter_starts_with_part_number","output the elements whose " +
                "partNumber field value starts with the given substring");
        this.productService = productService;
    }

    @Override
    public Response execute(ArgumentHolder argumentHolder, UserDTO userDTO) {
        ResponseBody responseBody = new ResponseBody();
        List<ProductDTO> productDTO = productService.filterPartNumber(argumentHolder.getPartNumber());
        StringBuilder sb = new StringBuilder();
        productDTO.forEach(product -> {
            sb.append(product.toString());
            sb.append("\n");
        });
        responseBody.addCommandResponseBody(sb.toString());
        return new Response(responseBody);
    }
}
