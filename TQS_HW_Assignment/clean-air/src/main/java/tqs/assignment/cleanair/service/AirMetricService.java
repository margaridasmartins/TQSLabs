package tqs.assignment.cleanair.service;

import tqs.assignment.cleanair.cache.AirMetricCache;
import tqs.assignment.cleanair.model.AirMetric;
import tqs.assignment.cleanair.utils.ExternalAPI;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * AirMetricService
 */
public class AirMetricService {

    @Autowired
    private AirMetricCache airMetricCache;

    @Autowired
    private ExternalAPI externalAPI;

    public Optional<List<AirMetric>> getCurrentAirMetricByLocation(String city){
        return null;
    }

    public Map<String,List<String>> getCities(){
        return null;
    }

    public Optional<List<AirMetric>> getAirMetricsByLocationAndTime(String city,int start, int end){
        return null;
    }

    
}