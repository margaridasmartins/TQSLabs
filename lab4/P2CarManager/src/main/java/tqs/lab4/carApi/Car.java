package tqs.lab4.carApi;


import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
@Data
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;

    @Column
    private String maker;

    @Column
    private String model;

    public Car(String maker,String model){
        this.maker=maker;
        this.model=model;
    }
}