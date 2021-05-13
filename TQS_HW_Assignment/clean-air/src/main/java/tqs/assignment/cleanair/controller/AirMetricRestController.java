package tqs.assignment.cleanair.controller;

import java.util.Map;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import tqs.assignment.cleanair.service.AirMetricService;
import tqs.assignment.cleanair.service.CacheStatisticsService;
import tqs.assignment.cleanair.model.AirMetric  ;

/**
 * AirMetricRestController
 */

@RestController
@RequestMapping("/api/v1")
public class AirMetricRestController {

    @Autowired
    public CacheStatisticsService cacheStatisticsService;

    @Autowired
    public AirMetricService airMetricService;

    @Autowired
    public Map<String,List<String>> districtMap;


    @GetMapping("/cities")
    @ResponseBody
    public  Map<String,List<String>> districtMap(){
        return districtMap;
    }

    @GetMapping("/cache/size")
    @ResponseBody
    public  Integer cacheSize(){
        return cacheStatisticsService.getCacheSize();
    }

    @GetMapping("/cache/requests")
    @ResponseBody
    public  Integer cacheRequests(){
        return cacheStatisticsService.getCacheRequests();
    }

    @GetMapping("/air/now/{city}")
    @ResponseBody
    public  List<AirMetric> currentMetric(@PathVariable(value="city") String city) throws IOException, MalformedURLException{
        return airMetricService.getCurrentAirMetricByLocation(city).get();
    }


}