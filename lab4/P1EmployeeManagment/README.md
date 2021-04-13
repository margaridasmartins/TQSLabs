###  a) Some AssertJ examples:

- EmployeeRepositoryTest
    
    - assertThat(fromDb.getEmail()).isEqualTo( emp.getEmail());
    - assertThat(allEmployees).hasSize(3).extracting(Employee::getName).containsOnly(alex.getName(), ron.getName(), bob.getName()); 

- EmployeeRestControllerIT

    - assertThat(found).extracting(Employee::getName).containsOnly("bob");

- EmployeeRestControllerTemplateIT

    - assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    - assertThat(found).extracting(Employee::getName).containsOnly("bob");

- EmployeeService_UnitTest

    - assertThat(allEmployees).hasSize(3).extracting(Employee::getName).contains(alex.getName(), john.getName(), bob.getName());

<br>

---
<br>

### b) Mock the behaviour of the repository

In *EmployeeService_UnitTest* class the *EmployeeRepository* is mocked using the annotation @Mock and then modeling the expected behaviour in the setUp() function.

<br>

---
<br>

### c) Difference between standard @Mock and @MockBean

Both anotations creat mock objects.

@Mock annotation is a shorthand for the Mockito.mock() method. It is usefull in unit tests.

The Spring Boot's @MockBean annotation is usefull in integration tests in order to mock a been (usually an external service like *EmployeeService* in the *EmployeeController_WithMockServiceIT* class)

<br>

---
<br>

### d) *application-integrationtest.properties* file

The *application-integrationtest.properties* file contains the details to configure the persistence storage.

The anotation *@TestPropertySource* commented in the *EmployeeRestControllerIT* class allows to configure the locations of properties files specific to the tests. In this case the configuration file to be used should be the *application-integrationtest.properties* file