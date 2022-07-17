package model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.enumtype.UnitOfMeasure;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product implements Comparable<Product>, Serializable,BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @OneToOne(optional = false, fetch = FetchType.LAZY,mappedBy = "product", cascade = CascadeType.ALL)
    private Coordinates coordinates;
    private LocalDate creationDate;
    private double price;
    private String partNumber;
    private long manufactureCost;
    @Enumerated(EnumType.STRING)
    private UnitOfMeasure unitOfMeasure;
    @OneToOne(optional = false, fetch = FetchType.LAZY,mappedBy = "product", cascade = CascadeType.ALL)
    private Person person;
    @ManyToOne(optional = false, fetch = FetchType.LAZY,cascade = {CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private User user;

    public Product(String name, Coordinates coordinates, double price, String partNumber, long manufactureCost, UnitOfMeasure unitOfMeasure, Person person) {
        this.setId(uniqueId());
        this.setName(name);
        this.setCoordinates(coordinates);
        this.setCreationDate();
        this.setPrice(price);
        this.partNumber = partNumber;
        this.manufactureCost = manufactureCost;
        this.unitOfMeasure = unitOfMeasure;
        this.setPerson(person);
    }

    public Product(long id, String name, Coordinates coordinates, LocalDate creationDate, double price, String partNumber, long manufactureCost, UnitOfMeasure unitOfMeasure, Person person) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.price = price;
        this.partNumber = partNumber;
        this.manufactureCost = manufactureCost;
        this.unitOfMeasure = unitOfMeasure;
        this.person = person;
    }



    /**
     *
     * @return id
     */
    private int uniqueId() {
        UUID uniqueKey = UUID.randomUUID();
        return Math.abs(uniqueKey.hashCode());
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     *
     * @param name
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param coordinates
     */

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCreationDate() {
        this.creationDate = LocalDate.now();
        //TODO исправить
/*        if (creationDate == null){
            throw new FieldException("Exception: Field 'creationDate' can not be null \n Please try again");
        }*/

    }

    /**
     *
     * @param price
     */

    public void setPrice(double price) {
        this.price = price;
    }

    /**
     *
     * @param owner
     */

    public void setPerson(Person owner) {
        this.person = owner;
    }

    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return partNumber
     */
    public String getPartNumber() {
        return partNumber;
    }

    /**
     *
     * @return price
     */
    public double getPrice() {
        return price;
    }

    /**
     *
     * @param o
     * @return
     */

    @Override
    public int compareTo(Product o) {
        return name.compareTo(o.getName());
    }

//    @Override
//    public int compareTo(model.Product o) {
//        return partNumber.compareTo(o.getPartNumber());
//    }

    public long getManufactureCost() {
        return manufactureCost;
    }

    public Long getId() {
        return id;
    }

    /**
     *
     * @return
     */



/*    @Override
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
    }*/

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public Person getPerson() {
        return person;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public void setManufactureCost(long manufactureCost) {
        this.manufactureCost = manufactureCost;
    }

    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }
}
