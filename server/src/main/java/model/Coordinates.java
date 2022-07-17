package model;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Coordinates implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Integer x; //Поле не может быть null

    private Long y; //Поле не может быть null
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Coordinates(Integer x, Long y) {
        this.setX(x);
        this.setY(y);
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public void setY(Long y) {
        this.y = y;
    }

    /**
     *
     * @return
     */

    @Override
    public String toString() {
        return " " +
                "x=" + x +
                ", y=" + y ;
    }
}
