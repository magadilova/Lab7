package dto.enumdto;

import java.io.Serializable;


public enum EyeColorDTO implements Serializable {
    GREEN("green"),
    BLUE("blue"),
    WHITE("white"),
    BROWN("brown");

    private final String eyeColor;

    EyeColorDTO(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    static public void showEyeColorsList() {
        for (EyeColorDTO color : values()) {
            System.out.println(color);
        }
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

    public String getEyeColor() {
        return eyeColor;
    }
}
