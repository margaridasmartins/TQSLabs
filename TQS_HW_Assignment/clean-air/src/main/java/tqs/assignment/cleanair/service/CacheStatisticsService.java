package tqs.assignment.cleanair.service;

import tqs.assignment.cleanair.cache.AirMetricCache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CacheStatisticsService
 */
@Service
public class CacheStatisticsService {

    @Autowired
    private  AirMetricCache airMetricCache;

    public int getCacheSize(){
        return 0;
    }

    public int getCacheRequests(){
        return 0;
    }
    
}