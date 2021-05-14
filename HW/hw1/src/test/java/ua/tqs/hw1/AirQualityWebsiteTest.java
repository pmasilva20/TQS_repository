package ua.tqs.hw1;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ua.tqs.hw1.AirQualityService;
import ua.tqs.hw1.Controller.AirQualityAPIController;
import ua.tqs.hw1.Controller.AirQualityController;
import ua.tqs.hw1.Datamodel.AirQuality;
import ua.tqs.hw1.Datamodel.Coordinates;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AirQualityController.class)
class AirQualityWebsiteTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    AirQualityService serviceMock;

    @Test
    void requestForIndex() throws Exception {
        Mockito.when(serviceMock.getAirQualityNow(Mockito.anyString(),Mockito.anyString(),Mockito.anyString())).thenReturn(null);
        mvc.perform(get("/airquality/index"))
                .andExpect(status().is(200))
                .andExpect(content().string(containsString("TQS-Homework")));
    }
    @Test
    void requestForCityNow() throws Exception {
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
        mvc.perform(get("/airquality/now?city=Lisbon"))
                .andExpect(status().is(200))
                .andExpect(content().string(containsString("TQS-Homework")))
                .andExpect(content().string(containsString("Air Quality in Lisbon")))
                .andExpect(content().string(containsString("Overall Quality:1/5")))
                .andExpect(content().string(containsString("Good Air Quality")))
                .andExpect(content().string(containsString("CO -&gt; 191.93")))
                .andExpect(content().string(containsString("NO -&gt; 0.0")))
                .andExpect(content().string(containsString("NO2 -&gt; 2.34")))
                .andExpect(content().string(containsString("O3 -&gt; 68.67")))
                .andExpect(content().string(containsString("SO2 -&gt; 1.37")))
                .andExpect(content().string(containsString("PM25 -&gt; 7.28")))
                .andExpect(content().string(containsString("PM10 -&gt; 11.94")))
                .andExpect(content().string(containsString("NH3 -&gt; 0.48")))
                .andExpect(content().string(containsString("The main pollutant was CO at 191.93")));
    }

}