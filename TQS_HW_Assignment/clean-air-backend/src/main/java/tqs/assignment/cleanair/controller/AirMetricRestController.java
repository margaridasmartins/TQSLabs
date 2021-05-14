package tqs.assignment.cleanair.controller;

import java.util.Map;
import java.util.Optional;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Collections;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tqs.assignment.cleanair.service.AirMetricService;
import tqs.assignment.cleanair.service.CacheStatisticsService;
import tqs.assignment.cleanair.model.AirMetric  ;

/**
 * AirMetricRestController
 */

@RestController
@RequestMapping("/api/v1")
public class AirMetricRestController {

    enum SORT {
        DATE,AQI
    }
    enum ORDER{
        DSC, ASC
    }

    @Autowired
    public CacheStatisticsService cacheStatisticsService;

    @Autowired
    public AirMetricService airMetricService;

    @Autowired
    public Map<String,List<String>> districtMap;


    @GetMapping("/cities")
    @ResponseBody
    public ResponseEntity<Map<String,List<String>>> districtsMap(){
        return ResponseEntity.ok().body(districtMap);
    }

    @GetMapping("/cache/stats")
    @ResponseBody
    public  ResponseEntity<Map<String,Integer>> cacheAllStats(){
        return ResponseEntity.ok().body(cacheStatisticsService.getAllStats());
    }

    @GetMapping("/cache/stats/size")
    @ResponseBody
    public  ResponseEntity<Integer>  cacheSize(){
        return ResponseEntity.ok().body(cacheStatisticsService.getCacheSize());
    }

    @GetMapping("/cache/stats/requests")
    @ResponseBody
    public  ResponseEntity<Integer> cacheRequests(){
        return ResponseEntity.ok().body(cacheStatisticsService.getCacheRequests());
    }

    @GetMapping("/cache/stats/hits")
    @ResponseBody
    public  ResponseEntity<Integer> cacheHits(){
        return ResponseEntity.ok().body(cacheStatisticsService.getCacheHits());
    }

    @GetMapping("/cache/stats/misses")
    @ResponseBody
    public  ResponseEntity<Integer> cacheMisses(){
        return ResponseEntity.ok().body(cacheStatisticsService.getCacheMisses());
    }

    @GetMapping("/air/now/{city}")
    @ResponseBody
    public ResponseEntity  currentMetric(@PathVariable(value="city") String city) throws IOException, MalformedURLException{
        Optional<List<AirMetric>> airMetric = airMetricService.getCurrentAirMetricByLocation(city);
        if(airMetric.isPresent()){
            return ResponseEntity.ok().body(airMetric.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not find data for this city check spelling" );
    }

    @GetMapping("/air/period/day/{city}")
    public ResponseEntity metricsByDayandLocation(@PathVariable(value="city") String city, @RequestParam(defaultValue = "-3") 
        int start, @RequestParam(defaultValue = "3") int end, @RequestParam(defaultValue = "DATE") 
        String sort, @RequestParam(defaultValue ="ASC") String order)throws IOException, MalformedURLException{
     
        if(!(start<-3 && end>3 && start>end)){
            LocalDateTime now = LocalDateTime.now();
            start = start * 24 -now.getHour();
            end = end*24 + (24-now.getHour());
            Optional<List<AirMetric>> optAirMetrics = airMetricService.getAirMetricsByLocationAndTime(city, start, end);

            if(optAirMetrics.isPresent()){
                List<AirMetric> airMetrics = optAirMetrics.get();
                if(sort.equals(SORT.DATE.name()) && order.equals(ORDER.ASC.name())){
                    Collections.sort(airMetrics, (a1, a2) -> a1.getMesureDate().compareTo(a2.getMesureDate()));
                }
                else if(sort.equals(SORT.DATE.name()) && order.equals(ORDER.DSC.name())){
                    Collections.sort(airMetrics, (a1, a2) -> a2.getMesureDate().compareTo(a1.getMesureDate()));
                }
                else if(sort.equals(SORT.AQI.name()) && order.equals(ORDER.ASC.name())){
                    Collections.sort(airMetrics, (a1, a2) -> a1.getAqi()-a2.getAqi());
                }
                else if(sort.equals(SORT.AQI.name()) && order.equals(ORDER.DSC.name())){
                    Collections.sort(airMetrics, (a1, a2) -> a2.getAqi()-a1.getAqi());
                }
                else{
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only sort DATE and AQI values are accepted\nOnly  order ASC and DSC values are accepted" );
                }
                return ResponseEntity.ok().body(airMetrics);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not find data for this city check spelling" );
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Start day must be less or equal than end day both need to be between -3 and 3" );
    }

    @GetMapping("/air/period/hour/{city}")
    @ResponseBody
    public ResponseEntity metricsByHourandLocation(@PathVariable(value="city") String city, @RequestParam(defaultValue = "-192") 
    int start, @RequestParam(defaultValue = "120") int end, @RequestParam(defaultValue = "DATE") 
    String sort, @RequestParam(defaultValue ="ASC") String order)throws IOException, MalformedURLException{
    
        Optional<List<AirMetric>> optAirMetrics = airMetricService.getAirMetricsByLocationAndTime(city, start, end);

        if(optAirMetrics.isPresent()){
            List<AirMetric> airMetrics = optAirMetrics.get();
            if(sort.equals(SORT.DATE.name()) && order.equals(ORDER.ASC.name())){
                Collections.sort(airMetrics, (a1, a2) -> a1.getMesureDate().compareTo(a2.getMesureDate()));
            }
            else if(sort.equals(SORT.DATE.name()) && order.equals(ORDER.DSC.name())){
                Collections.sort(airMetrics, (a1, a2) -> a2.getMesureDate().compareTo(a1.getMesureDate()));
            }
            else if(sort.equals(SORT.AQI.name()) && order.equals(ORDER.ASC.name())){
                Collections.sort(airMetrics, (a1, a2) -> a1.getAqi()-a2.getAqi());
            }
            else if(sort.equals(SORT.AQI.name()) && order.equals(ORDER.DSC.name())){
                Collections.sort(airMetrics, (a1, a2) -> a2.getAqi()-a1.getAqi());
            }
            else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only sort DATE and AQI values are accepted\nOnly  order ASC and DSC values are accepted" );
            }
            return ResponseEntity.ok().body(airMetrics);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            "Could not find data for this city or hours check spelling\n Can not forecast more than 120 hours\n Historical data goes no further from 192 hours" );

    }

   

}