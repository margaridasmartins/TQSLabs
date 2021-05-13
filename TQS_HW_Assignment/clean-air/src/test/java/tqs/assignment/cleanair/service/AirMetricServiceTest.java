package tqs.assignment.cleanair.service;

import tqs.assignment.cleanair.cache.AirMetricCache;
import tqs.assignment.cleanair.model.AirMetric;
import tqs.assignment.cleanair.utils.ExternalAPI;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.NoSuchElementException;


import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * AirMetricServiceTest
 */
@ExtendWith(MockitoExtension.class)
public class AirMetricServiceTest {

    @Mock
    private AirMetricCache airMetricCache;

    @Mock( lenient = true)
    private ExternalAPI extApi;

    @Mock( lenient = true)
    private Map<String,String[]> cityMap;

    @InjectMocks
    private AirMetricService airMetricService;

    private List<AirMetric> airMetrics;
    private List<AirMetric> airMetric_single;

    @BeforeEach
    public void setUp() {

        AirMetric airMetric = new AirMetric(2.0, 2.0,2.0,2.0,2.0,2.0,2.0,2.0,2, LocalDateTime.now());
        AirMetric airMetric2 = new AirMetric(2.0, 2.0,2.0,2.0,2.0,2.0,2.0,2.0,2, LocalDateTime.now());

        airMetrics = new ArrayList<AirMetric>();
        airMetric_single = new ArrayList<AirMetric>();

        airMetrics.add(airMetric);
        airMetrics.add(airMetric2);

        airMetric_single.add(airMetric);

        when(cityMap.containsKey("Porto")).thenReturn(true);
        when(cityMap.get("Porto")).thenReturn(new String[]{"41.1495","-8.6108"});
        when(cityMap.containsKey("CIDADE")).thenReturn(false);
    }

    @AfterEach
    public void tearDown() {
        airMetrics = null;
        airMetric_single= null;
    }

    @Test
    public void whenGetCurrentByLocationAirMetricsInCache_thenCacheShoulBeConsulted() throws IOException, MalformedURLException{
        
        when(airMetricCache.containsMetrics("Porto_NOW")).thenReturn(true);
        when(airMetricCache.getMetrics("Porto_NOW")).thenReturn(airMetric_single);
        assertEquals(airMetricService.getCurrentAirMetricByLocation("Porto").get(),airMetric_single, "AirMetric Service getting wrong cache Metric");
        verify(airMetricCache, VerificationModeFactory.times(1)).containsMetrics("Porto_NOW");
        verify(airMetricCache, VerificationModeFactory.times(1)).getMetrics("Porto_NOW");
    }

    @Test
    public void whenGetCurrentByLocationAirMetricsNotInCache_thenAPIShoulBeConsulted() throws IOException, MalformedURLException{
        String API_URL = "http://api.openweathermap.org/data/2.5/air_pollution?lat=41.1495&lon=-8.6108&appid=f7ddcbe1a3d60790175e59092cd49713";
        
        when(airMetricCache.containsMetrics("Porto_NOW")).thenReturn(false);
        when(extApi.getMetricsFromAPI(API_URL)).thenReturn(airMetric_single);
        assertEquals(airMetricService.getCurrentAirMetricByLocation("Porto").get(),airMetric_single, "AirMetric Service getting wrong api Metric");
        verify(airMetricCache, VerificationModeFactory.times(1)).containsMetrics("Porto_NOW");
        verify(extApi, VerificationModeFactory.times(1)).getMetricsFromAPI(API_URL);
        verify(airMetricCache, VerificationModeFactory.times(1)).addMetrics("Porto_NOW", airMetric_single);
    }

    @Test 
    public void whenGetCurrentByLocationInvalid_thenShouldReturnEmptyOptional() throws IOException, MalformedURLException{
        assertTrue(!airMetricService.getCurrentAirMetricByLocation("CIDADE").isPresent(),"AirMetric Service accepting wrong city values" );
    }

    @Test
    public void whenGetAirMetricsByLocationAndTimeInCache_thenCacheShoulBeConsulted() throws IOException, MalformedURLException{
        when(airMetricCache.containsMetrics("Porto_2_4")).thenReturn(true);
        when(airMetricCache.getMetrics("Porto_2_4")).thenReturn(airMetrics);
        assertEquals(airMetricService.getAirMetricsByLocationAndTime("Porto",2,4).get(),airMetrics, "AirMetric Service getting wrong cache Metric");
        verify(airMetricCache, VerificationModeFactory.times(1)).containsMetrics("Porto_2_4");
        verify(airMetricCache, VerificationModeFactory.times(1)).getMetrics("Porto_2_4");
    }

    @Test
    public void whenGetAirMetricsByLocationAndTimeNotInCache_thenAPIShoulBeConsulted() throws IOException, MalformedURLException{
        String API_URL = "http://api.openweathermap.org/data/2.5/air_pollution?history?lat=41.1495&lon=-8.6108&start=1606223802&end=1606482999&appid=f7ddcbe1a3d60790175e59092cd49713";
    
        when(airMetricCache.containsMetrics("Porto_2_4")).thenReturn(false);
        when(extApi.getMetricsFromAPI(any())).thenReturn(airMetrics);
        assertEquals(airMetricService.getAirMetricsByLocationAndTime("Porto",2,4).get(),airMetrics, "AirMetric Service getting wrong api Metrics");
        verify(airMetricCache, VerificationModeFactory.times(1)).containsMetrics("Porto_2_4");
        verify(extApi, VerificationModeFactory.times(1)).getMetricsFromAPI(any());
        verify(airMetricCache, VerificationModeFactory.times(1)).addMetrics("Porto_2_4", airMetrics);
    }

    @Test
    public void whenGetAirMetricsByLocationAndTimeInvalidTime_thenShouldReturnEmptyOptional() throws IOException, MalformedURLException{

        assertTrue(!airMetricService.getAirMetricsByLocationAndTime("Porto",-200,0).isPresent(),"AirMetric Service accepting wrong past time values" );
        assertTrue(!airMetricService.getAirMetricsByLocationAndTime("Porto",30,200).isPresent(),"AirMetric Service accepting wrong future time values" );
        assertTrue(!airMetricService.getAirMetricsByLocationAndTime("Porto",30,20).isPresent(),"AirMetric Service accepting wrong time values" );
    }

    @Test
    public void whenGetAirMetricsByLocationAndTimeInvalidLocation_thenShouldReturnEmptyOptional() throws IOException, MalformedURLException{
        assertTrue(!airMetricService.getAirMetricsByLocationAndTime("CIDADE",-200,0).isPresent(),"AirMetric Service accepting wrong city values" );
    }
    
}

