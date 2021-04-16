package tqs.lab4.carApi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>{

    Car findByCarId(Long id);
    List<Car> findAll();
}