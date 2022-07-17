package dto.enumdto;

import java.io.Serializable;


public enum CountryDTO implements Serializable {
    FRANCE("france"),
    INDIA("india"),
    ITALY("italy");

    private final String country;

    CountryDTO(String country) {
        this.country = country;
    }

    public static void showCountryList(){
        for ( CountryDTO country : values()){
            System.out.println(country);
        }
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
