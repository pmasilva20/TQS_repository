package ua.tqs.hw1.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.tqs.hw1.AirQualityService;
import ua.tqs.hw1.Datamodel.AirQuality;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class AirQualityAPIController {

    Logger logger = LoggerFactory.getLogger(AirQualityAPIController.class);

    @Autowired
    private AirQualityService service;

    @GetMapping(path = "/now")
    public AirQuality getAirQualityNow(
                                   @RequestParam(value = "city",required = true) String city,
                                   @RequestParam(value = "state",required = false) String state,
                                   @RequestParam(value = "country",required = false) String country){
        String log = "API request for current time and city:"+city;
        if(state != null)log+=",state:"+state;
        if(country != null)log+=",country:"+country;
        logger.info(log);
        return service.getAirQualityNow(city,state,country);
    }

    @GetMapping(path = "/statistics")
    public Map<String, Integer> getCacheStatistics()
    {
        logger.info("API request for cache");
        return service.getCacheStats();
    }
}