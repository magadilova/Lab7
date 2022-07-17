package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoordinatesDTO implements Serializable {
    private static final long serialVersionUID = -4671974091798472705L;
    private long id;
    private Integer x; //Поле не может быть null
    private Long y; //Поле не может быть null

    public CoordinatesDTO(Integer x, Long y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return " " +
                "x=" + x +
                ", y=" + y ;
    }
}
