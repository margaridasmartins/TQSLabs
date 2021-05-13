package tqs.assignment.cleanair.service;

import tqs.assignment.cleanair.cache.AirMetricCache;

import java.util.Map;
import java.util.HashMap;

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
        return airMetricCache.getSize();
    }

    public int getCacheRequests(){
        return airMetricCache.getRequests();
    }

    public int getCacheMisses(){
        return airMetricCache.getMisses();
    }

    public int getCacheHits(){
        return airMetricCache.getHits();
    }

    public Map<String,Integer> getAllStats(){
        Map<String,Integer> stats = new HashMap<String,Integer>();

        stats.put("SIZE", airMetricCache.getSize());
        stats.put("HITS", airMetricCache.getHits());
        stats.put("MISSES", airMetricCache.getMisses());
        stats.put("REQUESTS", airMetricCache.getRequests());
        return stats;
    } 
    
}