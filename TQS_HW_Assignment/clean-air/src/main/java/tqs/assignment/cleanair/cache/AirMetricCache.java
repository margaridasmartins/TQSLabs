package tqs.assignment.cleanair.cache;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
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

    enum STATISTICS {
        REQUESTS, SIZE, HITS, MISSES
    }

    private Map<String,Pair<LocalDateTime,List<AirMetric>>> airMetricCache;
    private Map<STATISTICS, Integer> statistics;

    public AirMetricCache(){
        this.airMetricCache = new HashMap<String,Pair<LocalDateTime,List<AirMetric>>>();
        this.statistics = new HashMap<STATISTICS,Integer>();
        this.statistics.put(STATISTICS.REQUESTS,0);
        this.statistics.put(STATISTICS.SIZE,0);
        this.statistics.put(STATISTICS.HITS,0);
        this.statistics.put(STATISTICS.MISSES,0);
    }

    public void addMetrics(String query,List<AirMetric> airMetrics){
        if(!this.containsMetrics(query)){
            this.statistics.put(STATISTICS.SIZE, this.statistics.get(STATISTICS.SIZE)+1);
        }
        this.airMetricCache.put(query,Pair.of(LocalDateTime.now().plus(30,ChronoUnit.MINUTES ),airMetrics));
    }

    public boolean containsMetrics(String query){
        if (airMetricCache.containsKey(query)){
            if(LocalDateTime.now().compareTo(airMetricCache.get(query).getKey()) <0){
                return true;
            }
            airMetricCache.remove(query);
            this.statistics.put(STATISTICS.SIZE, this.statistics.get(STATISTICS.SIZE)-1);
        }
        return false;
    }

    public Optional<List<AirMetric>> getMetrics(String query){
        this.statistics.put(STATISTICS.REQUESTS, this.statistics.get(STATISTICS.REQUESTS)+1);
        if(this.containsMetrics(query)){
            this.statistics.put(STATISTICS.HITS, this.statistics.get(STATISTICS.HITS)+1);
            return Optional.of(airMetricCache.get(query).getValue());
        }
        else{
            this.statistics.put(STATISTICS.MISSES, this.statistics.get(STATISTICS.MISSES)+1);  
            return Optional.empty();
        }
    }

    public int getSize(){
        return this.statistics.get(STATISTICS.SIZE);
    }

    public int getRequests(){
        return this.statistics.get(STATISTICS.REQUESTS);
    }

    public int getHits(){
        return this.statistics.get(STATISTICS.HITS);
    }

    public int getMisses(){
        return this.statistics.get(STATISTICS.MISSES);
    }


}