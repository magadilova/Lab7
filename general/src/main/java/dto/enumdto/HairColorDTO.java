package dto.enumdto;

import java.io.Serializable;


public enum HairColorDTO implements Serializable {
    BLACK("black"),
    BLUE("blue"),
    YELLOW("yellow"),
    BROWN("brown");

    private final String hairColor;

    HairColorDTO(String hairColor) {
        this.hairColor = hairColor;
    }

    static public void showHairColorList() {
        for (HairColorDTO color : values()){
            System.out.println(color);
        }
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
