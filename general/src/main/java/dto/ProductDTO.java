package dto;


import dto.enumdto.UnitOfMeasureDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO implements Serializable {
    private static final long serialVersionUID = -8397896299449811875L;
    private long id;
    private String name;
    private CoordinatesDTO coordinates;
    private LocalDate creationDate;
    private double price;
    private String partNumber;
    private long manufactureCost;
    private UnitOfMeasureDTO unitOfMeasure;
    private PersonDTO person;
    private UserDTO user;

    public ProductDTO(String name, CoordinatesDTO coordinates, LocalDate creationDate, double price, String partNumber, long manufactureCost, UnitOfMeasureDTO unitOfMeasure, PersonDTO person) {
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.price = price;
        this.partNumber = partNumber;
        this.manufactureCost = manufactureCost;
        this.unitOfMeasure = unitOfMeasure;
        this.person = person;
    }

    @Override
    public String toString() {
        return "Product :\n " +
                "Id : " + id +
                ",\n Name : '" + name + '\'' +
                ",\n Coordinates :" + coordinates.toString() +
                ",\n Creation date : " + creationDate.toString() +
                ",\n Price : " + price +
                ",\n Part number : '" + partNumber + '\'' +
                ",\n Manufacture cost : " + manufactureCost +
                ",\n Unit of measure : " + unitOfMeasure +
                ",\n Owner :  " + person.toString();
    }
}
