package ua.tqs.hw1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.tqs.hw1.Datamodel.AirQuality;
import ua.tqs.hw1.Datamodel.Coordinates;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class AirQualityCacheTest {

    private AirQualityCache aqiRepository = new AirQualityCache(30);

    @Test
    void whenFindAlreadyCached_ReturnCached() {
        long timenow = (System.currentTimeMillis()/1000);
        AirQuality aq = new AirQuality("Lisbon",1,timenow,1620960149);
        aq.setCoordinates(new Coordinates(38.7167,-9.1333));
        aqiRepository.putInInstance(aq);
        AirQuality cached = aqiRepository.findByCoordinates(new Coordinates(38.7167,-9.1333));
        assertThat(aq).isEqualTo(cached);
        assertThat(aqiRepository.getCountRequestsSuccess()).isEqualTo(1);
    }

    @Test
    void whenFindNotCached_ReturnNull() {
        AirQuality cached = aqiRepository.findByCoordinates(new Coordinates(38.7167,-9.1333));
        assertThat(cached).isNull();
        assertThat(aqiRepository.getCountRequestsFailures()).isEqualTo(1);
    }


    @Test
    void whenFindCachedAfterTimeout_ReturnNull() throws InterruptedException {
        long timenow = (System.currentTimeMillis()/1000);
        AirQuality aq = new AirQuality("Lisbon",1,timenow,1620960149);
        aq.setCoordinates(new Coordinates(38.7167,-9.1333));
        aqiRepository.putInInstance(aq);
        TimeUnit.SECONDS.sleep(31);
        AirQuality cached = aqiRepository.findByCoordinates(new Coordinates(38.7167,-9.1333));
        assertThat(cached).isNull();
        assertThat(aqiRepository.getCountRequestsFailures()).isEqualTo(1);
    }


}