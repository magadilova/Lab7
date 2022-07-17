package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationDTO implements Serializable {
    private static final long serialVersionUID = -787915095923007670L;
    private long id;
    private float x;
    private double y;
    private double z;

    public LocationDTO(float x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return
                "x=" + x +
                        ", y=" + y +
                        ", z=" + z ;
    }
}
