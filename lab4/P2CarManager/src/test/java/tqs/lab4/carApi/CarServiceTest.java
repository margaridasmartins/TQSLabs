package tqs.lab4.carApi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarManagerService service;

    private Car car;

    @BeforeEach
    public void setUp() {
        car = new Car("opel","corsa"); 
    }

    @Test
    public void whenValidId_thenCarShouldBeFound() {
        
        when(carRepository.findByCarId(car.getCarId())).thenReturn(car);
        Optional<Car> car_found = service.getCarDetails(car.getCarId());
        assertThat(car_found.get().getMaker()).isEqualTo("opel");
        verify(carRepository, VerificationModeFactory.times(1)).findByCarId(car.getCarId());
    }

    @Test
    public void whenInValidId_thenEmployeeShouldNotBeFound() {
        when(carRepository.findByCarId(10L)).thenReturn(null);
        Optional<Car> car_found = service.getCarDetails(10L);
        verify(carRepository, VerificationModeFactory.times(1)).findByCarId(10L);
        assertThat(car_found.isEmpty()).isEqualTo(true);
    }

    @Test
    public void given3Cars_whengetAll_thenReturn3Records() {
        Car car1 = new Car("mercedes","vito");
        Car car2 = new Car("ford","fiesta");
        List<Car> allCars = Arrays.asList(car, car1, car2);

        when(carRepository.findAll()).thenReturn(allCars);

        List<Car> cars_found = service.getAllCars();
        
        verify(carRepository, VerificationModeFactory.times(1)).findAll();
        assertThat(cars_found).hasSize(3).extracting(Car::getMaker).contains(car.getMaker(), car1.getMaker(), car2.getMaker());
    }

    @Test
    public void whensave_thenCarShouldBeSaved() {
        
        
        when(carRepository.save(car)).thenReturn(car);

        Car car_saved = service.save(car);
        
        verify(carRepository, VerificationModeFactory.times(1)).save(car);
        assertThat(car_saved.getMaker()).isEqualTo("opel");
    }
}