package ua.tqs.hw1;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ua.tqs.hw1.Datamodel.AirQuality;
import ua.tqs.hw1.Datamodel.Coordinates;

import java.util.Map;

@Service
@Transactional
public class AirQualityService {

    Logger logger = LoggerFactory.getLogger(AirQualityService.class);
    public static final int TTL_TIME = 30;
    public static final String LIMIT_GEOCODING = "3";
    private static final String APPID_OPENWEATHER = "c29e89ae7c997ec2c8685d" + LIMIT_GEOCODING + "1fea6ded4";
    private AirQualityCache airQualityCache = new AirQualityCache(TTL_TIME);
    private RestTemplate restTemplate = new RestTemplate();

    public AirQuality getAirQualityNow(String city, String state, String country) {
        if(city == null) return null;
        //Do reverse geocoding
        String query = getQuery(city, state, country);
        if(logger.isInfoEnabled()) logger.info(String.format("Geocoding for the following query:'%s'", query));
        Coordinates coordinates = reverseGeocoding(query);
        if(logger.isInfoEnabled() && coordinates == null){
            logger.error(String.format("Geocoding for query '%s' return null", query));
            return null;
        }
        //Check if any for city at this time,else go get it and return
        AirQuality resultGotten = airQualityCache.findByCoordinates(coordinates);
        if(resultGotten != null){
            return resultGotten;
        }
        if(logger.isInfoEnabled() && coordinates != null){
            logger.info(String.format("Requesting new pollution data for the coordinates:(%s,%s)", coordinates.getLat(), coordinates.getLon()));
        }

        if(coordinates == null)return null;
        String url = getURLOpenWeather(coordinates);
        ResponseEntity<String> response = restTemplate.getForEntity(url,String.class);
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(response.getBody());
            JsonNode list = root.path("list");
            JsonNode recordJson = list.get(0);
            AirQuality result = jsonMapper(recordJson,city,state,country);
            result.setCoordinates(coordinates);
            airQualityCache.putInInstance(result);
            return result;
        } catch (JsonProcessingException e) {
            logger.error(String.format("Error while processing json:'%s'", e.toString()));
        }
        return null;
    }

    private String getURLOpenWeather(Coordinates coordinates) {
        return "http://api.openweathermap.org/data/2.5/air_pollution?lat="+coordinates.getLat()+"&lon="+coordinates.getLon()+"&appid="+ APPID_OPENWEATHER;
    }

    private String getQuery(String city, String state, String country) {
        String query = "";
        query+=city;
        if(state != null && !state.isEmpty()) query+=","+state;
        if(country != null && !country.isEmpty()) query+=","+country;
        return query;
    }

    private Coordinates reverseGeocoding(String query) {
        String url = "http://api.openweathermap.org/geo/1.0/direct?q="+query+ "&limit=" + LIMIT_GEOCODING + "&appid=" +APPID_OPENWEATHER;
        ResponseEntity<String> response = restTemplate.getForEntity(url,String.class);
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(response.getBody());
            JsonNode recordJson = root.get(0);
            double lat = Double.parseDouble(recordJson.get("lat").asText());
            double lon = Double.parseDouble(recordJson.get("lon").asText());
            return new Coordinates(lat,lon);
        } catch (JsonProcessingException e) {
            logger.error(String.format("Error while processing json:'%s'", e));
        }
        return null;
    }

    private AirQuality jsonMapper(JsonNode recordGotten,String city,String state,String country) {
        String components = "components";
        int aqi = recordGotten.get("main").get("aqi").asInt();
        double co = recordGotten.get(components).get("co").asDouble();
        double no = recordGotten.get(components).get("no").asDouble();
        double no2 = recordGotten.get(components).get("no2").asDouble();
        double o3 = recordGotten.get(components).get("o" + LIMIT_GEOCODING).asDouble();
        double so2 = recordGotten.get(components).get("so2").asDouble();
        double pm2_5 = recordGotten.get(components).get("pm2_5").asDouble();
        double pm10 = recordGotten.get(components).get("pm10").asDouble();
        double nh3 = recordGotten.get(components).get("nh" + LIMIT_GEOCODING).asDouble();
        long timestamp = recordGotten.get("dt").asLong();
        long dateTimestamp = (System.currentTimeMillis()/1000);
        AirQuality result = new AirQuality(city,aqi,dateTimestamp,timestamp);
        result.setCo(co);
        result.setNo(no);
        result.setNo2(no2);
        result.setO3(o3);
        result.setSo2(so2);
        result.setPm2_5(pm2_5);
        result.setPm10(pm10);
        result.setNh3(nh3);
        if(state != null && !state.isEmpty()) result.setState(state);
        if(country != null && !country.isEmpty()) result.setCountry(country);
        return result;
    }

    public Map<String, Integer> getCacheStats() {
        return airQualityCache.getStats();
    }
}
