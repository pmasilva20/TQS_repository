package ua.tqs.hw1.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.tqs.hw1.AirQualityService;
import ua.tqs.hw1.Datamodel.AirQuality;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/airquality")
public class AirQualityController {
    Logger logger = LoggerFactory.getLogger(AirQualityController.class);
    @Autowired
    private AirQualityService service;

    @GetMapping(path = "/index")
    public String getAirQuality(Model model){
        String log = "Website request for index";
        logger.info(log);
        return "index";
    }

    @GetMapping(path = "/now")
    public String getAirQualityNow(Model model,
            @RequestParam(value = "city",required = true) String city,
            @RequestParam(value = "state",required = false) String state,
            @RequestParam(value = "country",required = false) String country){
        AirQuality result = service.getAirQualityNow(city,state,country);
        makeModel(model, result);
        String log = "Website request for current time and city:"+city;
        if(state != null)log+=",state:"+state;
        if(country != null)log+=",country:"+country;
        logger.info(log);
        return "main";
    }

    private void makeModel(Model model, AirQuality result) {
        String location = result.getCity();
        if(result.getState() != null && !result.getState().isEmpty()) location+=","+result.getState();
        if(result.getCountry() != null && !result.getCountry().isEmpty()) location+=","+result.getCountry();
        model.addAttribute("location",location);
        Date dateGot = new Date(result.getDate() * 1000);
        String dataFormated = new SimpleDateFormat("dd/MM/yyyy").format(dateGot);
        model.addAttribute("date", dataFormated);
        model.addAttribute("aqi",result.getAir_quality());
        model.addAttribute("co",result.getCo());
        model.addAttribute("no",result.getNo());
        model.addAttribute("no2",result.getNo2());
        model.addAttribute("o3",result.getO3());
        model.addAttribute("so2",result.getSo2());
        model.addAttribute("pm2_5",result.getPm2_5());
        model.addAttribute("pm10",result.getPm10());
        model.addAttribute("nh3",result.getNh3());
        String mainPollutant = "CO";
        double mainPollutantValue = result.getCo();
        if(mainPollutantValue < result.getNo()) {mainPollutant = "NO"; mainPollutantValue = result.getNo();}
        if(mainPollutantValue < result.getNo()) {mainPollutant = "NO"; mainPollutantValue = result.getNo();}
        if(mainPollutantValue < result.getNo2()) {mainPollutant = "NO2"; mainPollutantValue = result.getNo2();}
        if(mainPollutantValue < result.getO3()) {mainPollutant = "O3"; mainPollutantValue = result.getO3();}
        if(mainPollutantValue < result.getSo2()) {mainPollutant = "SO2"; mainPollutantValue = result.getSo2();}
        if(mainPollutantValue < result.getPm2_5()) {mainPollutant = "Pm25"; mainPollutantValue = result.getPm2_5();}
        if(mainPollutantValue < result.getPm10()) {mainPollutant = "Pm10"; mainPollutantValue = result.getPm10();}
        if(mainPollutantValue < result.getNh3()) {mainPollutant = "NH3"; mainPollutantValue = result.getNh3();}
        model.addAttribute("main_pollutant",mainPollutant);
        model.addAttribute("main_pollutant_value",mainPollutantValue);
    }

}