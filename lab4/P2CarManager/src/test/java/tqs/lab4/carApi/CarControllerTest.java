package tqs.lab4.carApi;

import org.springframework.http.MediaType;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.CoreMatchers.is;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.List;
import java.util.Arrays;
import java.util.Optional;

@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarManagerService service;

    private Car car;
    
    @BeforeEach
    public void setUp() throws Exception {
        car = new Car("opel","corsa");
    }

    @Test
    public void whenPostCar_thenCreateCar() throws Exception{
        when(service.save(car)).thenReturn(car);

        mvc.perform(post("/api/cars").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(car)))
            .andExpect(status().isCreated()).andExpect(jsonPath("$.maker", is(car.getMaker())))
            .andExpect(jsonPath("$.model", is(car.getModel())));
        
        verify(service, times(1)).save(car);
    }

    @Test
    public void whenGetCars_thenReturnJsonArray() throws Exception {

        Car car1 = new Car("mercedes","vito");
        Car car2 = new Car("ford","fiesta");

        List<Car> allCars = Arrays.asList(car, car1, car2);

        when(service.getAllCars()).thenReturn(allCars);

        mvc.perform(get("/api/cars").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(3))).andExpect(jsonPath("$[0].maker", is(car.getMaker()))).andExpect(jsonPath("$[1].maker", is(car1.getMaker())))
            .andExpect(jsonPath("$[2].maker", is(car2.getMaker())));

        verify(service, VerificationModeFactory.times(1)).getAllCars();

    }
    
    @Test
    public void whenGetCar_thenReturnJson() throws Exception{
        when(service.getCarDetails(1L)).thenReturn(Optional.of(car));

        mvc.perform(get("/api/cars/1").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(car)))
            .andExpect(status().isOk()).andExpect(jsonPath("$.maker", is(car.getMaker())))
            .andExpect(jsonPath("$.model", is(car.getModel())));
        
        verify(service, times(1)).getCarDetails(1L);
    }
}