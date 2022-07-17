package dto;


import dto.enumdto.CountryDTO;
import dto.enumdto.EyeColorDTO;
import dto.enumdto.HairColorDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO implements Serializable {
    private static final long serialVersionUID = 4212730512421257331L;
    private long id;
    private String name; //Поле не может быть null, Строка не может быть пустой
    private String passportID; //Значение этого поля должно быть уникальным, Поле не может быть null
    private EyeColorDTO eyeColor; //Поле не может быть null
    private HairColorDTO hairColor; //Поле не может быть null
    private CountryDTO nationality; //Поле может быть null
    private LocationDTO location; //Поле может быть null

    public PersonDTO(String name, String passportID, EyeColorDTO eyeColor, HairColorDTO hairColor, CountryDTO nationality, LocationDTO location) {
        this.name = name;
        this.passportID = passportID;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.nationality = nationality;
        this.location = location;
    }

    public PersonDTO(String name, EyeColorDTO eyeColor, HairColorDTO hairColor, CountryDTO nationality, LocationDTO location) {
        this.name = name;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.nationality = nationality;
        this.location = location;
    }



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
