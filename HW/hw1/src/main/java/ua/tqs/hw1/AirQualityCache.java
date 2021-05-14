package ua.tqs.hw1;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.tqs.hw1.Datamodel.AirQuality;
import ua.tqs.hw1.Datamodel.Coordinates;

import java.util.HashMap;
import java.util.Map;

public class AirQualityCache {

    Logger logger = LoggerFactory.getLogger(AirQualityCache.class);

    private Map<Coordinates,AirQuality> cache;
    private int countRequestsSuccess;
    private int countRequestsFailures;
    private int countRequestsTotal;
    private final int TTL_TIME;

    public AirQualityCache(int TTL_TIME) {
        this.cache = new HashMap<>();
        this.countRequestsSuccess = 0;
        this.countRequestsFailures = 0;
        this.countRequestsTotal = 0;
        this.TTL_TIME = TTL_TIME;
    }

    AirQuality findByCoordinates(Coordinates coordinates){
        this.countRequestsTotal+=1;
        if(cache.containsKey(coordinates)){
            AirQuality aqi = cache.get(coordinates);
            long timenow = (System.currentTimeMillis()/1000);
            if(!(timenow - aqi.getTimestamp_request() > TTL_TIME)){
                this.countRequestsSuccess+=1;
                logger.info("Cache used");
                return aqi;
            }
        }
        this.countRequestsFailures+=1;
        return null;
    }
    AirQuality putInInstance(AirQuality ai){
        if(cache.containsKey(ai.getCoordinates())){
            cache.replace(ai.getCoordinates(),ai);
        }
        else {
            cache.put(ai.getCoordinates(),ai);
        }
        return ai;
    }

    public Map<String, Integer> getStats(){
        Map<String,Integer> cacheStats = new HashMap<String,Integer>();
        cacheStats.put("Count of Successful Requests to Cache",this.countRequestsSuccess);
        cacheStats.put("Count of Failed Requests to Cache",this.countRequestsFailures);
        cacheStats.put("Count of Total Requests made to Cache",this.countRequestsTotal);
        cacheStats.put("Size of Cache at the moment",this.cache.size());
        return cacheStats;
    }

    public int getCountRequestsSuccess() {
        return countRequestsSuccess;
    }

    public int getCountRequestsFailures() {
        return countRequestsFailures;
    }

    public int getCountRequestsTotal() {
        return countRequestsTotal;
    }
}
