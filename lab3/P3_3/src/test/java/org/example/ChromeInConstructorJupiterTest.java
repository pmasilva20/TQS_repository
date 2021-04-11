package org.example;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

@ExtendWith(SeleniumJupiter.class)
class ChromeInConstructorJupiterTest {

    ChromeDriver driver;

    ChromeInConstructorJupiterTest(ChromeDriver driver) {
        this.driver = driver;
    }

    @Test
    void testGlobalChrome() {
        driver.get("https://bonigarcia.github.io/selenium-jupiter/");
        assertThat(driver.getTitle(),
                containsString("JUnit 5 extension for Selenium"));
    }
    @Test
    void testHeadless(HtmlUnitDriver driver){
        driver.get("https://www.ua.pt");
        assertThat(driver.getTitle(), containsString("Universidade de Aveiro"));
    }

}