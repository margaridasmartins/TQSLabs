package tqs.assignment.cleanair.cache;

import tqs.assignment.cleanair.cache.AirMetricCache;
import tqs.assignment.cleanair.model.AirMetric;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.lang.Thread;



/**
 * CacheTest
 */
public class CacheTest {

    private AirMetricCache airMetricCache;
    private List<AirMetric> airMetrics;

    @BeforeEach
    public void setUp() {

        //Create cache
        airMetricCache = new AirMetricCache();

        //populate cache
        AirMetric airMetric = new AirMetric(2.0, 2.0,2.0,2.0,2.0,2.0,2.0,2.0,2, LocalDateTime.now());
        AirMetric airMetric2 = new AirMetric(2.0, 2.0,2.0,2.0,2.0,2.0,2.0,2.0,2, LocalDateTime.now());

        airMetrics = new ArrayList<AirMetric>();
        airMetrics.add(airMetric);
        airMetrics.add(airMetric2);
    }

    @AfterEach
    public void tearDown() {
        airMetricCache = null;
        airMetrics= null;
    }

    @Test
    public void testAddMetrics(){
        airMetricCache.addMetrics("Porto_22_99", airMetrics);
        assertTrue( airMetricCache.containsMetrics("Porto_22_99"), "addMetrics: added metrics not found in cache." );
        assertTrue(airMetricCache.getSize()==1, "addMetrics: added metrics size not 1" );
    }

    @Test
    public void testTTL() throws InterruptedException{

        assertTrue( !airMetricCache.containsMetrics("Porto_22_99"), "ContainsMetrics: metric should not exist" );
        airMetricCache.addMetrics("Porto_22_99", airMetrics);
        assertTrue(airMetricCache.containsMetrics("Porto_22_99"), "ContainsMetrics: metric should  exist");
        //Thread.sleep(310*1000);
        //assertTrue(!airMetricCache.containsMetrics("Porto_22_99"), "ContainsMetrics: metric should  not exist");
    }

    @Test
    public void testGetMetricValid(){
        airMetricCache.addMetrics("Porto_22_99", airMetrics);
        assertEquals(airMetricCache.getMetrics("Porto_22_99").get(),airMetrics ,"getMetricsValid: get Metrics value wrong" );
    }

    @Test
    public void testGetMetricInexistent(){
        assertEquals(Optional.empty(), airMetricCache.getMetrics("Porto_22_99"), "getMetricInexistent: get Metrics did not return empty");
    }

    
    @Test
    public void testStatRequest(){
        airMetricCache.addMetrics("Porto_22_99", airMetrics);

        airMetricCache.getMetrics("Porto_22_99");
        airMetricCache.getMetrics("Porto_22_99");
        airMetricCache.getMetrics("Porto_22_99");

        assertEquals(airMetricCache.getRequests(),3, "StatRequest wrong number of requests");
    }

    @Test
    public void testStatSize(){
        airMetricCache.addMetrics("Porto_22_99", airMetrics);
        airMetricCache.addMetrics("Porto_22_99", airMetrics);
        airMetricCache.addMetrics("Porto_10_99", airMetrics);

        assertEquals(airMetricCache.getSize(),2, "StatSize wrong size of cache");
    }

    @Test
    public void testStatMisses(){
        airMetricCache.addMetrics("Porto_22_99", airMetrics);
        airMetricCache.getMetrics("Porto_22_99");
        airMetricCache.getMetrics("Porto_10_99");
        airMetricCache.getMetrics("Porto_10_99");

        assertEquals(airMetricCache.getMisses(),2, "StatSize wrong misses number");
    }

    @Test
    public void testStatHits(){
        airMetricCache.addMetrics("Porto_22_99", airMetrics);
        airMetricCache.getMetrics("Porto_22_99");
        airMetricCache.getMetrics("Porto_10_99");
        airMetricCache.getMetrics("Porto_10_99");
        airMetricCache.getMetrics("Porto_10_99");

        assertEquals(airMetricCache.getHits(),1, "StatSize wrong hits number");
    }


}