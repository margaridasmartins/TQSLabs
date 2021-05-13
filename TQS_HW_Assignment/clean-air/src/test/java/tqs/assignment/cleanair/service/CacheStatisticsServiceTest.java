package tqs.assignment.cleanair.service;

import tqs.assignment.cleanair.cache.AirMetricCache;
import tqs.assignment.cleanair.model.AirMetric;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.internal.verification.VerificationModeFactory;

/**
 * CacheStatisticsServiceTest
 */
@ExtendWith(MockitoExtension.class)
public class CacheStatisticsServiceTest {

    @Mock(lenient=true)
    private AirMetricCache airMetricCache;

    @InjectMocks
    private CacheStatisticsService cacheStatisticsService;

    @BeforeEach
    public void setUp() {
        when(airMetricCache.getSize()).thenReturn(5);
        when(airMetricCache.getRequests()).thenReturn(3);
        when(airMetricCache.getHits()).thenReturn(3);
        when(airMetricCache.getMisses()).thenReturn(4);
    }

    @Test
    public void whenGetSize_thenSizeShouldBeReturned(){
        assertEquals(cacheStatisticsService.getCacheSize(),5, "Cache Service getting wrong cache size");
        verify(airMetricCache, VerificationModeFactory.times(1)).getSize();
    }

    @Test
    public void whenGetRequests_thenRequestsShouldBeReturned(){
        assertEquals(cacheStatisticsService.getCacheRequests(),3, "Cache Service getting wrong cache request number");
        verify(airMetricCache, VerificationModeFactory.times(1)).getRequests();
    }

    @Test
    public void whenGetHits_thenHitsShouldBeReturned(){
        assertEquals(cacheStatisticsService.getCacheHits(),3, "Cache Service getting wrong cache hit number");
        verify(airMetricCache, VerificationModeFactory.times(1)).getHits();
    }

    @Test
    public void whenGetMisses_thenMissesShouldBeReturned(){
        assertEquals(cacheStatisticsService.getCacheMisses(),4, "Cache Service getting wrong cache miss number");
        verify(airMetricCache, VerificationModeFactory.times(1)).getMisses();
    }

    @Test
    public void whenGetAllStats_thenAllStatsShouldBeReturned(){
        Map<String,Integer> stats = new HashMap<String,Integer>();

        stats.put("SIZE", 5);
        stats.put("HITS", 3);
        stats.put("MISSES", 4);
        stats.put("REQUESTS", 3);

        assertEquals(cacheStatisticsService.getAllStats(),stats, "Cache Service getting wrong overall stats");
    }



}