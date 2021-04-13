package tqsdemo.employeemngr.employee;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void whenFindAlexByName_thenReturnAlexEmployee() {
        Employee alex = new Employee("alex", "alex@deti.com");
        entityManager.persistAndFlush(alex); //ensure data is persisted at this point

        Employee found = employeeRepository.findByName(alex.getName());
        assertThat( found ).isEqualTo(alex);
    }

    @Test
    public void whenInvalidEmployeeName_thenReturnNull() {
        Employee fromDb = employeeRepository.findByName("Does Not Exist");
        assertThat(fromDb).isNull();
    }

    @Test
    public void whenFindEmployedByExistingId_thenReturnEmployee() {
        Employee emp = new Employee("test", "test@deti.com");
        entityManager.persistAndFlush(emp);

        Employee fromDb = employeeRepository.findById(emp.getId()).orElse(null);
        assertThat(fromDb).isNotNull();
        assertThat(fromDb.getEmail()).isEqualTo( emp.getEmail());
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        Employee fromDb = employeeRepository.findById(-111L).orElse(null);
        assertThat(fromDb).isNull();
    }

    @Test
    public void givenSetOfEmployees_whenFindAll_thenReturnAllEmployees() {
        Employee alex = new Employee("alex", "alex@deti.com");
        Employee ron = new Employee("ron", "ron@deti.com");
        Employee bob = new Employee("bob", "bob@deti.com");

        entityManager.persist(alex);
        entityManager.persist(bob);
        entityManager.persist(ron);
        entityManager.flush();

        List<Employee> allEmployees = employeeRepository.findAll();

        assertThat(allEmployees).hasSize(3).extracting(Employee::getName).containsOnly(alex.getName(), ron.getName(), bob.getName());
    }

}