package interaction;

import dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;


@Data
@AllArgsConstructor
public class ArgumentHolder  implements Serializable {
    private static final long serialVersionUID = -8236189770445550538L;
    private ProductDTO productDTO;
    private double price;
    private long id;
    private String partNumber;

    public ArgumentHolder(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }

    public ArgumentHolder(double price) {
        this.price = price;
    }

    public ArgumentHolder(long id) {
        this.id = id;
    }

    public ArgumentHolder(String partNumber) {
        this.partNumber = partNumber;
    }
}
