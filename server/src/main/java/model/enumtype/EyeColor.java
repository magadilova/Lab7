package model.enumtype;

import java.io.Serializable;

public enum EyeColor implements Serializable {
    GREEN("green"),
    BLUE("blue"),
    WHITE("white"),
    BROWN("brown");

    private final String eyeColor;

    EyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    static public void showEyeColorsList() {
        for (EyeColor color : values()) {
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
