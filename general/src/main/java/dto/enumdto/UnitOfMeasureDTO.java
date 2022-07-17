package dto.enumdto;

import java.io.Serializable;


public enum UnitOfMeasureDTO implements Serializable {

    CENTIMETERS("centimeters"),
    MILLILITERS("milliliters"),
    MILLIGRAMS("milligrams");



    private final String unitOfMeasure;

    UnitOfMeasureDTO(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public static void showUnitOfMeasureList(){
        for ( UnitOfMeasureDTO unitOfMeasure : values()){
            System.out.println(unitOfMeasure);
        }
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

}
