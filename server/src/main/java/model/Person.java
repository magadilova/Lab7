package model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.enumtype.Country;
import model.enumtype.EyeColor;
import model.enumtype.HairColor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name; //Поле не может быть null, Строка не может быть пустой
    private String passportID; //Значение этого поля должно быть уникальным, Поле не может быть null
    @Enumerated(EnumType.STRING)
    private EyeColor eyeColor; //Поле не может быть null
    @Enumerated(EnumType.STRING)
    private HairColor hairColor; //Поле не может быть null
    @Enumerated(EnumType.STRING)
    private Country nationality; //Поле может быть null
    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private Location location; //Поле может быть null
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;


    public Person(String name, EyeColor eyeColor, HairColor haircolor, Country nationality, Location location) {
        this.setName(name);
        this.passportID = String.valueOf(this.uniqueId());
        this.setEyeColor(eyeColor);
        this.setHaircolor(haircolor);
        this.setNationality(nationality);
        this.location = location;
    }

    /**
     * @param name
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return passportID
     */
    private int uniqueId() {
        UUID uniqueKey = UUID.randomUUID();
        return Math.abs(uniqueKey.hashCode());
    }

    /**
     * @param eyeColor
     */

    public void setEyeColor(EyeColor eyeColor) {
        this.eyeColor = eyeColor;
    }

    /**
     * @param hairColor
     */

    public void setHaircolor(HairColor hairColor) {
        this.hairColor = hairColor;
    }

    /**
     * @param nationality
     */

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }


    /**
     * @return
     */

    @Override
    public String toString() {
        return
                "\n\t Name : '" + name + '\'' +
                        ",\n\t Passport ID : '" + passportID + '\'' +
                        ",\n\t Eye color : " + eyeColor +
                        ",\n\t Hair color : " + hairColor +
                        ",\n\t Nationality : " + nationality +
                        ",\n\t Location : " + location.toString();
    }
}
