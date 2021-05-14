package ua.tqs.hw1;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ua.tqs.hw1.Controller.AirQualityAPIController;
import ua.tqs.hw1.Datamodel.AirQuality;
import ua.tqs.hw1.Datamodel.Coordinates;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AirQualityAPIController.class)
class AirQualityAPITest {

    @Autowired
    MockMvc mvc;

    @MockBean
    AirQualityService serviceMock;

    @Test
    void lackOfData() throws Exception {
        Mockito.when(serviceMock.getAirQualityNow(Mockito.anyString(),Mockito.anyString(),Mockito.anyString())).thenReturn(null);
        mvc.perform(get("/api/now?city=")).andExpect(content().string(""));
    }
    @Test
    void invalidQueryProvided() throws Exception {
        Mockito.when(serviceMock.getAirQualityNow(Mockito.anyString(),Mockito.anyString(),Mockito.anyString())).thenReturn(null);
        mvc.perform(get("/api/now?city=QWERTY")).andExpect(content().string(""));
    }

    @Test
    void validQueryProvided() throws Exception {
        AirQuality aq = new AirQuality("Lisbon",1,1620961149,1620960149);
        aq.setCoordinates(new Coordinates(38.7167,-9.1333));
        aq.setCo(191.93);
        aq.setNo(0);
        aq.setNo2(2.34);
        aq.setO3(68.67);
        aq.setSo2(1.37);
        aq.setPm2_5(7.28);
        aq.setPm10(11.94);
        aq.setNh3(0.48);
        Mockito.when(serviceMock.getAirQualityNow(Mockito.anyString(),Mockito.any(),Mockito.any())).thenReturn(aq);
        mvc.perform(get("/api/now?city=Lisbon"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("city", is("Lisbon")))
                .andExpect(jsonPath("coordinates.lat", is(38.7167)))
                .andExpect(jsonPath("coordinates.lon", is(-9.1333)))
                .andExpect(jsonPath("air_quality", is(1)))
                .andExpect(jsonPath("co", is(191.93)))
                .andExpect(jsonPath("no", is(0.0)))
                .andExpect(jsonPath("no2", is(2.34)))
                .andExpect(jsonPath("o3", is(	68.67)))
                .andExpect(jsonPath("pm2_5", is(7.28)))
                .andExpect(jsonPath("pm10", is(11.94)))
                .andExpect(jsonPath("nh3", is(0.48)))
                .andExpect(jsonPath("timestamp_request", is(1620961149)))
                .andExpect(jsonPath("date", is(1620960149)));
    }

    @Test
    void validCacheStatisticsReturned() throws Exception {
        Map<String, Integer> result = new HashMap<>();
        result.put("Count of Failed Requests to Cache",0);
        result.put("Count of Total Requests made to Cache",0);
        result.put("Size of Cache at the moment",0);
        result.put("Count of Successful Requests to Cache",0);
        Mockito.when(serviceMock.getCacheStats()).thenReturn(result);
        mvc.perform(get("/api/statistics")).andExpect(status().is(200))
                .andExpect(jsonPath("['Count of Failed Requests to Cache']", is(0)))
                .andExpect(jsonPath("['Count of Total Requests made to Cache']", is(0)))
                .andExpect(jsonPath("['Size of Cache at the moment']", is(0)))
                .andExpect(jsonPath("['Count of Successful Requests to Cache']", is(0)));
    }
}