package tqs.assignment.cleanair.cache;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.HashMap;
import org.apache.commons.lang3.tuple.Pair;
import java.time.LocalDateTime;
import tqs.assignment.cleanair.model.AirMetric;
import java.time.temporal.ChronoUnit;

/**
 * AirMetricCache
 */

@Component
public class AirMetricCache {

    private Map<String,Pair<LocalDateTime,List<AirMetric>>> airMetricCache;
    private Map<String, Integer> statistics;

    public AirMetricCache(){
        this.airMetricCache = new HashMap<String,Pair<LocalDateTime,List<AirMetric>>>();
        this.statistics = new HashMap<String,Integer>();
        this.statistics.put("REQUESTS",0);
        this.statistics.put("SIZE",0);
    }

    public void addMetrics(String query,List<AirMetric> airMetrics){
        if(!this.containsMetrics(query)){
            this.statistics.put("SIZE", this.statistics.get("SIZE")+1);
        }
        this.airMetricCache.put(query,Pair.of(LocalDateTime.now().plus(30,ChronoUnit.MINUTES ),airMetrics));
    }

    public boolean containsMetrics(String query){
        if (airMetricCache.containsKey(query)){
            if(LocalDateTime.now().compareTo(airMetricCache.get(query).getKey()) <0){
                return true;
            }
            airMetricCache.remove(query);
            this.statistics.put("SIZE", this.statistics.get("SIZE")-1);
        }
        return false;
    }

    public List<AirMetric> getMetrics(String query) throws NoSuchElementException{
        if(this.containsMetrics(query)){
            this.statistics.put("REQUESTS", this.statistics.get("REQUESTS")+1);
            return airMetricCache.get(query).getValue();
        }
        throw new NoSuchElementException();
    }

    public int getSize(){
        return this.statistics.get("SIZE");
    }

    public int getRequests(){
        return this.statistics.get("REQUESTS");
    }

}