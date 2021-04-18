package com.ua.car_TQS;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class CarControllerTest {

    @Autowired
    MockMvc testServlet;

    @MockBean
    CarManagerService carService;


    @Test
    void whenPostCar_thenReturnCreatedCar() throws Exception{
        Car kia1 = new Car("kia", "stinger");
        kia1.setCarId(111L);
        when(carService.save(Mockito.any())).thenReturn(kia1);

        testServlet.perform(post("/api/cars").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(kia1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$maker", is("kia")));

        verify(carService, times(1)).save(kia1);
    }

    @Test
    public void givenCars_whenGetCars_thenReturnJsonArray() throws Exception {
        Car toyota = new Car("toyota", "corolla");
        Car peugeot = new Car("peugeot", "308");
        Car mini = new Car("mini", "cooper");

        List<Car> allCars = Arrays.asList(toyota,peugeot,mini);

        given(carService.getAllCars()).willReturn(allCars);

        testServlet.perform(get("/api/cars").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].maker", is(toyota.getMaker())))
                .andExpect(jsonPath("$[1].maker", is(peugeot.getMaker())))
                .andExpect(jsonPath("$[2].maker", is(mini.getMaker())));
        verify(carService, VerificationModeFactory.times(1)).getAllCars();

    }
}