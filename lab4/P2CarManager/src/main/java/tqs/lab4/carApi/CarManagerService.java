package tqs.lab4.carApi;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CarManagerService {

    public Car save(Car car){
        return car;
    }
    
    public List<Car>  getAllCars(){
        return null;
    }

    public Optional<Car> getCarDetails(Long id){
        return null;
    }
}