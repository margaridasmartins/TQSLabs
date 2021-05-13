package tqs.assignment.cleanair.service;

import tqs.assignment.cleanair.cache.AirMetricCache;
import tqs.assignment.cleanair.model.AirMetric;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.List;
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

    @Mock
    private AirMetricCache airMetricCache;

    @InjectMocks
    private CacheStatisticsService cacheStatisticsService;

    @Test
    public void whenGetSize_thenSizeShouldBeReturned(){
        when(airMetricCache.getSize()).thenReturn(5);
        assertEquals(cacheStatisticsService.getCacheSize(),5, "Cache Service getting wrong cache size");
        verify(airMetricCache, VerificationModeFactory.times(1)).getSize();
    }

    @Test
    public void whenGetRequests_thenRequestsShouldBeReturned(){
        when(airMetricCache.getRequests()).thenReturn(3);
        assertEquals(cacheStatisticsService.getCacheRequests(),3, "Cache Service getting wrong cache request number");
        verify(airMetricCache, VerificationModeFactory.times(1)).getRequests();
    }


}