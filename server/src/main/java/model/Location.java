package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private float x;
    private double y;
    private double z;
    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;



    public Location(float x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     *
     * @return
     */

    @Override
    public String toString() {
        return
                "x=" + x +
                        ", y=" + y +
                        ", z=" + z ;
    }
}
