package tqs.assignment.cleanair.service;

import tqs.assignment.cleanair.cache.AirMetricCache;
import tqs.assignment.cleanair.model.AirMetric;
import tqs.assignment.cleanair.utils.ExternalAPI;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.TimeZone;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * AirMetricService
 */
@Service
public class AirMetricService {

    private final String API_URL_NOW = "http://api.openweathermap.org/data/2.5/air_pollution?lat=%s&lon=%s&appid=f7ddcbe1a3d60790175e59092cd49713";
    private final String API_URL_HIST = "http://api.openweathermap.org/data/2.5/air_pollution/history?lat=%s&lon=%s&start=%s&end=%s&appid=f7ddcbe1a3d60790175e59092cd49713";

    @Autowired
    private AirMetricCache airMetricCache;

    @Autowired
    private ExternalAPI externalAPI;

    @Autowired
    public Map<String,String[]> cityMap;

    @Autowired
    public Map<String,List<String>> districts_cities ;

    public Optional<List<AirMetric>> getCurrentAirMetricByLocation(String city) throws IOException, MalformedURLException{
        if(cityMap.containsKey(city)){
            if(airMetricCache.containsMetrics(city+"_NOW")){
                return Optional.of(airMetricCache.getMetrics(city+"_NOW"));
            }
            String[] coord =cityMap.get(city);
            List<AirMetric> airMetric = externalAPI.getMetricsFromAPI(String.format(API_URL_NOW, coord[0],coord[1] ));
            airMetricCache.addMetrics(city+"_NOW", airMetric);
            return Optional.of(airMetric);
        }
        return Optional.empty();
    }

    public Map<String,List<String>> getCities(){
        return districts_cities;
    }

    public Optional<List<AirMetric>> getAirMetricsByLocationAndTime(String city,int start, int end)throws IOException, MalformedURLException{
        if(cityMap.containsKey(city)){
            
            if(start>-168 && end<72 && start<=end){
                String query = String.format("%s_%s_%s",city, String.valueOf(start),String.valueOf(end));
                if(airMetricCache.containsMetrics(query)){
                    return Optional.of(airMetricCache.getMetrics(query));
                }
                String[] coord =cityMap.get(city);

                
                ZoneId zoneId = TimeZone.getTimeZone("GMT+1").toZoneId();
                LocalDateTime time_start = LocalDateTime.now().plus(start,ChronoUnit.HOURS);
                LocalDateTime time_end = LocalDateTime.now().plus(end,ChronoUnit.HOURS);
                long timestamp_start = time_start.atZone(zoneId).toEpochSecond();
                long timestamp_end = time_end.atZone(zoneId).toEpochSecond();

                List<AirMetric> airMetric = externalAPI.getMetricsFromAPI(String.format(API_URL_HIST, coord[0],coord[1],time_start,time_end));
                airMetricCache.addMetrics(query, airMetric);
                return Optional.of(airMetric);
            }

        }
        return Optional.empty();
    }

    
}