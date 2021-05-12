package tqs.assignment.cleanair.cache;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.HashMap;
import org.apache.commons.lang3.tuple.Pair;
import java.time.LocalDateTime;
import tqs.assignment.cleanair.model.AirMetric;

/**
 * AirMetricCache
 */

public class AirMetricCache {

    private Map<String,Pair<LocalDateTime,List<AirMetric>>> airMetricCache;
    private Map<String, Integer> statistics;

    public AirMetricCache(){
        this.airMetricCache = new HashMap<String,Pair<LocalDateTime,List<AirMetric>>>();
        this.statistics = new HashMap<String,Integer>();
        this.statistics.put("Requests",0);
    }

    public void addMetrics(String query,List<AirMetric> airMetrics){
        
    }

    public boolean containsMetrics(String query){
        return false;
    }

    public List<AirMetric> getMetrics(String query) throws NoSuchElementException{
        return null;
    }

    public int getStat(String statistic){
        return 0;
    }

}