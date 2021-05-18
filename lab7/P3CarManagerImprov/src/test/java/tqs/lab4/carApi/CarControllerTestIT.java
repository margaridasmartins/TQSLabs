package tqs.lab4.carApi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.io.IOException;
import java.util.List;

import org.springframework.test.context.TestPropertySource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = CarApiApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "application-integrationtest.properties")
public class CarControllerTestIT {

    @Container
  	public static MySQLContainer container = new MySQLContainer().withUsername("admin").withPassword("admin").withDatabaseName("cars");

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
    }

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CarRepository carRepository;

    private Car car;

    @BeforeEach
    public void setUp() {
        car = new Car("opel","corsa"); 
    }

    @AfterEach
    public void resetDb() {
        carRepository.deleteAll();
    }

    @Test
    public void whenValidCar_thenCreateCar() throws IOException, Exception {
        mvc.perform(post("/api/cars").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(car)));

        List<Car> car_found = carRepository.findAll();
        assertThat(car_found).extracting(Car::getMaker).containsOnly("opel");
    }

    @Test
    public void givenCars_whenGetAllCars_thenStatus200() throws IOException, Exception {
        Car car1 = new Car("mercedes","vito");
        Car car2 = new Car("ford","fiesta");

        carRepository.save(car);
        carRepository.save(car1);
        carRepository.save(car2);

        mvc.perform(get("/api/cars").contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(3))))
            .andExpect(jsonPath("$[0].maker", is("opel")))
            .andExpect(jsonPath("$[1].maker", is("mercedes")))
            .andExpect(jsonPath("$[2].maker", is("ford")));

    }

    @Test
    public void whenValidIdCar_thenStatus200() throws IOException, Exception {
        carRepository.save(car);


        mvc.perform(get("/api/cars/1").contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.maker", is(car.getMaker())));
    }


}
