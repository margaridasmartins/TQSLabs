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
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
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
        RestAssuredMockMvc.mockMvc(mvc);
    }

    @Test
    public void whenPostCar_thenCreateCar() throws Exception{
        when(service.save(car)).thenReturn(car);
        RestAssuredMockMvc.given()
            .header("Content-type", "application/json")
            .and()
            .body(car)
            .when()
            .post("/api/cars")
            .then()
            .statusCode(201);
    }

    @Test
    public void whenGetCars_thenReturnJsonArray() throws Exception {

        Car car1 = new Car("mercedes","vito");
        Car car2 = new Car("ford","fiesta");

        List<Car> allCars = Arrays.asList(car, car1, car2);

        when(service.getAllCars()).thenReturn(allCars);

        RestAssuredMockMvc.when().get("/api/cars").then().body("maker", hasItems("mercedes","ford")).statusCode(200);

        verify(service, VerificationModeFactory.times(1)).getAllCars();

    }
    
    @Test
    public void whenGetCar_thenReturnJson() throws Exception{
        when(service.getCarDetails(1L)).thenReturn(Optional.of(car));
        
        RestAssuredMockMvc.when().get("/api/cars/1").then().body("maker", equalTo("opel")).statusCode(200);       
        
        verify(service, times(1)).getCarDetails(1L);
    }
}
