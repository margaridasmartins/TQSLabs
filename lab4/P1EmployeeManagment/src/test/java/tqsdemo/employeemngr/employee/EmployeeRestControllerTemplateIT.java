package tqsdemo.employeemngr.employee;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

// switch AutoConfigureTestDatabase with TestPropertySource to use a real database
//@TestPropertySource( locations = "application-integrationtest.properties")

@AutoConfigureTestDatabase
public class EmployeeRestControllerTemplateIT {

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private EmployeeRepository repository;

    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }


    @Test
    public void whenValidInput_thenCreateEmployee() {
        Employee bob = new Employee("bob", "bob@deti.com");
        ResponseEntity<Employee> entity = restTemplate.postForEntity("/api/employees", bob, Employee.class);

        List<Employee> found = repository.findAll();
        assertThat(found).extracting(Employee::getName).containsOnly("bob");
    }

    @Test
    public void givenEmployees_whenGetEmployees_thenStatus200()  {
        createTestEmployee("bob", "bob@deti.com");
        createTestEmployee("alex", "alex@deti.com");


        ResponseEntity<List<Employee>> response = restTemplate
                .exchange("/api/employees", HttpMethod.GET, null, new ParameterizedTypeReference<List<Employee>>() {
                });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).extracting(Employee::getName).containsExactly("bob", "alex");

    }


    private void createTestEmployee(String name, String email) {
        Employee emp = new Employee(name, email);
        repository.saveAndFlush(emp);
    }

}
