package tqs;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;


public class APIRestAssuredTest {

    private final String API_URL = "https://jsonplaceholder.typicode.com/todos";

    @Test
    public void whenRequestGet_thenOK(){
        when().request("GET", API_URL).then().statusCode(200);
    }

    @Test 
    public void whenRequestGetToDo_thenReturnToDo(){
        when().request("GET", API_URL+"/4").then().assertThat().body("title", equalTo("et porro tempora"));
    }

    @Test
    public void whenRequestAllToDo_thenReturnAllToDO(){
        when().request("GET", API_URL).then().body("id", hasItems(198,199));
    }   
}