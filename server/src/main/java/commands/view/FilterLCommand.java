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
 * выводит элементы, значение поля price которых меньше заданного
 */

public class FilterLCommand extends AbstractCommand {
    private final ProductService productService;

    public FilterLCommand(ProductService productService) {
        super("filter_less_than_price","output the elements whose value of the price " +
                "field is less than the given value");
        this.productService = productService;
    }

    @Override
    public Response execute(ArgumentHolder argumentHolder, UserDTO userDTO) {
        ResponseBody responseBody = new ResponseBody();
        List<ProductDTO> productDTO = productService.filterLower(argumentHolder.getPrice());
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        productDTO.forEach(product -> {
            sb.append(product.toString());
            sb.append("\n");
        });
        responseBody.addCommandResponseBody(sb.toString());
        return new Response(responseBody);
    }
}
