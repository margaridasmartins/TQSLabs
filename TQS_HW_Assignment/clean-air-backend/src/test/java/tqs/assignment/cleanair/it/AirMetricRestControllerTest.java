package tqs.assignment.cleanair;

import tqs.assignment.cleanair.CleanAirApplication;
import tqs.assignment.cleanair.service.AirMetricService;
import tqs.assignment.cleanair.service.CacheStatisticsService;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;


import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.http.MediaType;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * AirMetricRestControllerTest_IT
 */

@SpringBootTest( webEnvironment = WebEnvironment.RANDOM_PORT,classes =CleanAirApplication.class)
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class AirMetricRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private AirMetricService airMetricService;

    @Autowired
    private CacheStatisticsService cacheStatisticsService;

    @Test 
    @Order(1)    
    public void whenGetCacheStats_thenstatus200() throws Exception{
        airMetricService.getAirMetricsByLocationAndTime("Porto", 0, 1);
        mvc.perform(get("/api/v1/cache/stats").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.HITS", is(0)))
            .andExpect(jsonPath("$.REQUESTS", is(1)))
            .andExpect(jsonPath("$.SIZE", is(1)))
            .andExpect(jsonPath("$.MISSES", is(1)));
    }

    @Test 
    @Order(2)
    public void whenNoTimeSpecified_thenGetAllValues() throws Exception{
        mvc.perform(get("/api/v1/air/period/day/Braga").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))));

        mvc.perform(get("/api/v1/air/period/hour/Braga").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))));

    }

    @Test 
    @Order(3)
    public void whenGetNow_thenReturnOneValue() throws Exception{
        mvc.perform(get("/api/v1/air/now/Braga").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$", hasSize(is(1))));
    }

    @Test 
    @Order(4)
    public void whenOrderDiffernt_thenReturnValuesInDifferentOrder() throws Exception{
        MvcResult result = mvc.perform(get("/api/v1/air/period/day/Braga?order=DSC").contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
            .andReturn();
            
        String q1 = result.getResponse().getContentAsString();

        MvcResult result2 =mvc.perform(get("/api/v1/air/period/day/Braga?order=ASC").contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
            .andReturn();
            
        String q2 = result2.getResponse().getContentAsString();

        assertTrue(!q2.equals(q1), "Different orders should present diferent results");

        MvcResult result3 = mvc.perform(get("/api/v1/air/period/hour/Braga?order=DSC").contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
            .andReturn();
            
        String q3 = result3.getResponse().getContentAsString();

        MvcResult result4 =mvc.perform(get("/api/v1/air/period/hour/Braga?order=ASC").contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
            .andReturn();
            
        String q4 = result4.getResponse().getContentAsString();

        assertTrue(!q3.equals(q4), "Different orders should present diferent results");

    }

    @Order(5)
    public void whenSortDiffernt_thenReturnValuesInDifferentOrder() throws Exception{
        MvcResult result = mvc.perform(get("/api/v1/air/period/day/Braga?sort=DATE").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
            .andReturn();
            
        String q1 = result.getResponse().getContentAsString();

        MvcResult result2 =mvc.perform(get("/api/v1/air/period/day/Braga?sort=AQI").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
            .andReturn();
            
        String q2 = result2.getResponse().getContentAsString();

        assertTrue(!q2.equals(q1), "Different sorts should present diferent results");

        MvcResult result3 = mvc.perform(get("/api/v1/air/period/hour/Braga?sort=DATE").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
            .andReturn();
            
        String q3 = result3.getResponse().getContentAsString();

        MvcResult result4 =mvc.perform(get("/api/v1/air/period/hour/Braga?sort=AQI").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
            .andReturn();
            
        String q4 = result4.getResponse().getContentAsString();

        assertTrue(!q4.equals(q3), "Different sorts should present diferent results");



    }

    @Test
    @Order(6)
    public void whenInvalidCity_thenStatus404() throws Exception{
        mvc.perform(get("/api/v1/air/period/day/CIDADE"))
        .andDo(print())
        .andExpect(status().isNotFound());

        mvc.perform(get("/api/v1/air/period/hour/CIDADE"))
        .andDo(print())
        .andExpect(status().isNotFound());

        mvc.perform(get("/api/v1/air/now/CIDADE"))
        .andDo(print())
        .andExpect(status().isNotFound());
    }

    @Test
    @Order(7)
    public void whenInvalidParameters_thenStatus400() throws Exception{
        mvc.perform(get("/api/v1/air/period/day/Porto?sort=I"))
        .andDo(print())
        .andExpect(status().isBadRequest());

        mvc.perform(get("/api/v1/air/period/hour/Porto?order=Z"))
        .andDo(print())
        .andExpect(status().isBadRequest());

    }

}