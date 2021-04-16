package tqs.lab4.carApi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CarRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired 
    private CarRepository carRepository;


    private Car car;

    @BeforeEach
    public void setUp() {
        car = new Car("opel","corsa"); 
    }

    

    @Test
    public void whenInvalidId_thenReturnNull() {
        Car car_db = carRepository.findByCarId(10L);
        assertThat(car_db).isNull();
    }

    @Test
    public void whenValidId_thenReturnCar() {

        entityManager.persistAndFlush(car);
        Car car_db = carRepository.findByCarId(car.getCarId());
        assertThat(car_db).isNotNull();
        assertThat(car_db.getMaker()).isEqualTo(car.getMaker());
    }

    @Test
    public void given3Cars_whenFindAll_thenReturn3Records() {
        Car car1 = new Car("mercedes","vito");
        Car car2 = new Car("ford","fiesta");

        entityManager.persist(car);
        entityManager.persist(car1);
        entityManager.persist(car2);
        entityManager.flush();

        List<Car> cars_db = carRepository.findAll();

        assertThat(cars_db).hasSize(3).extracting(Car::getMaker).containsOnly(car.getMaker(), car1.getMaker(), car2.getMaker());
    }

}