package ua.tqs.hw1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.tqs.hw1.Datamodel.AirQuality;
import ua.tqs.hw1.Datamodel.Coordinates;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class AirQualityServiceTest {
    @Mock( lenient = true)
    private AirQualityCache airQualityCache;

    @InjectMocks
    private AirQualityService airQualityService;

    @Test
    void whenValidQueryParametersGetFromAPI_thenAirQualityFound() {
        long timenow = (System.currentTimeMillis()/1000);
        AirQuality aq = new AirQuality("Lisbon",1,timenow,1620960149);
        aq.setCoordinates(new Coordinates(38.7167,-9.1333));
        Mockito.when(airQualityService.getAirQualityNow("Lisbon",null,null)).thenReturn(aq);

        AirQuality aqi = airQualityService.getAirQualityNow("Lisbon",null,null);

        assertThat(aqi).isNotNull();
    }

    @Test
    void whenNotValidQueryParametersGetFromAPI_thenReturnNull() {
        AirQuality aqi = airQualityService.getAirQualityNow(null,"Lisbon",null);
        assertThat(aqi).isNull();
    }
    @Test
    void whenValidQueryParametersGetFromCache_thenAirQualityFound() {
        long timenow = (System.currentTimeMillis()/1000);
        AirQuality aq = new AirQuality("Lisbon",1,timenow,1620960149);
        aq.setCoordinates(new Coordinates(38.7167,-9.1333));
        Mockito.when(airQualityCache.findByCoordinates(aq.getCoordinates())).thenReturn(aq);

        AirQuality aqi = airQualityCache.findByCoordinates(new Coordinates(38.7167,-9.1333));

        assertThat(aqi).isNotNull();
        verifyFindByCoordinatesIsCalledOnce(new Coordinates(38.7167,-9.1333));
    }

    @Test
    void whenNotValidQueryParametersGetFromCache_thenReturnNull() {
        Mockito.when(airQualityCache.findByCoordinates(new Coordinates(-10000,90000))).thenReturn(null);
        AirQuality aqi = airQualityCache.findByCoordinates(new Coordinates(-10000,90000));
        assertThat(aqi).isNull();
    }

    @Test
    void whenGetCache_thenReturnStatistics() {
        Map<String, Integer> cache = new HashMap<>();
        cache.put("Count of Successful Requests to Cache",0);
        cache.put("Count of Failed Requests to Cache",0);
        cache.put("Count of Total Requests made to Cache",0);
        cache.put("Size of Cache at the moment",0);
        Mockito.when(airQualityCache.getStats()).thenReturn(cache);
        Map<String, Integer> stats = airQualityService.getCacheStats();
        assertThat(stats).isNotNull();
        assertThat(stats.containsKey("Count of Successful Requests to Cache")).isTrue();
        assertThat(stats.containsKey("Count of Failed Requests to Cache")).isTrue();
        assertThat(stats.containsKey("Count of Total Requests made to Cache")).isTrue();
        assertThat(stats.containsKey("Size of Cache at the moment")).isTrue();
    }

    private void verifyFindByCoordinatesIsCalledOnce(Coordinates coords) {
        Mockito.verify(airQualityCache, VerificationModeFactory.times(1)).findByCoordinates(coords);
    }

}