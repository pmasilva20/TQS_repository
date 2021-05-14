package ua.tqs.hw1;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SeleniumJupiter.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AirQualitySeleniumInterfaceTest {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;

    AirQualitySeleniumInterfaceTest(ChromeDriver driver) {
        this.driver = driver;
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }
    @Test
    @Order(0)
    void cacheTest1() {
        driver.get("http://localhost:8080/api/statistics");
        driver.manage().window().setSize(new Dimension(1297, 741));
        driver.findElement(By.cssSelector("pre")).click();
        driver.findElement(By.cssSelector("pre")).click();
        driver.findElement(By.cssSelector("html")).click();
        assertThat(driver.findElement(By.cssSelector("pre")).getText(), is("{\"Count of Failed Requests to Cache\":0,\"Count of Total Requests made to Cache\":0,\"Size of Cache at the moment\":0,\"Count of Successful Requests to Cache\":0}"));
        driver.findElement(By.cssSelector("pre")).click();
    }
    @Test
    @Order(1)
    void websiteTest1() {
        driver.get("http://localhost:8080/airquality/index");
        driver.manage().window().setSize(new Dimension(1297, 741));
        driver.findElement(By.cssSelector("p:nth-child(2)")).click();
        driver.findElement(By.cssSelector("p:nth-child(2)")).click();
        driver.findElement(By.cssSelector("p:nth-child(2)")).click();
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("p:nth-child(2)"));
            assert(elements.size() > 0);
        }
        driver.findElement(By.cssSelector(".jumbotron")).click();
        driver.findElement(By.id("city")).click();
        driver.findElement(By.id("city")).sendKeys("Lisbon");
        driver.findElement(By.cssSelector(".col-sm-2:nth-child(1)")).click();
        driver.findElement(By.cssSelector(".btn > p")).click();
        driver.findElement(By.cssSelector("h4")).click();
        assertThat(driver.findElement(By.cssSelector("h4")).getText(), is("Air Quality in Lisbon"));
        driver.findElement(By.cssSelector(".jumbotron")).click();
        driver.findElement(By.cssSelector(".jumbotron > p:nth-child(2)")).click();
        assertThat(driver.findElement(By.cssSelector(".jumbotron > p:nth-child(2)")).getText(), is("Overall Quality:2/5"));
        driver.findElement(By.cssSelector(".col-sm-8")).click();
        driver.findElement(By.cssSelector(".col-sm-8 > p:nth-child(2)")).click();
        assertThat(driver.findElement(By.cssSelector(".col-sm-8 > p:nth-child(2)")).getText(), containsString("CO ->"));
        driver.findElement(By.cssSelector("p:nth-child(5)")).click();
        driver.findElement(By.cssSelector(".col-sm-8")).click();
        assertThat(driver.findElement(By.cssSelector(".col-sm-8 > p:nth-child(3)")).getText(), containsString("NO ->"));
        driver.findElement(By.cssSelector("p:nth-child(4)")).click();
        driver.findElement(By.cssSelector(".col-sm-8")).click();
        assertThat(driver.findElement(By.cssSelector("p:nth-child(4)")).getText(), containsString("NO2 ->"));
        driver.findElement(By.cssSelector(".col-sm-8")).click();
        driver.findElement(By.cssSelector(".col-sm-8")).click();
        assertThat(driver.findElement(By.cssSelector("p:nth-child(5)")).getText(),containsString("O3 ->"));
        driver.findElement(By.cssSelector(".col-sm-8")).click();
        driver.findElement(By.cssSelector(".col-sm-8")).click();
        assertThat(driver.findElement(By.cssSelector("p:nth-child(6)")).getText(), containsString("SO2 ->"));
        driver.findElement(By.cssSelector("p:nth-child(6)")).click();
        driver.findElement(By.cssSelector("p:nth-child(7)")).click();
        assertThat(driver.findElement(By.cssSelector("p:nth-child(7)")).getText(), containsString("PM25 ->"));
        driver.findElement(By.cssSelector(".col-sm-8")).click();
        driver.findElement(By.cssSelector("p:nth-child(8)")).click();
        assertThat(driver.findElement(By.cssSelector("p:nth-child(8)")).getText(), containsString("PM10 ->"));
        driver.findElement(By.cssSelector("p:nth-child(8)")).click();
        driver.findElement(By.cssSelector("p:nth-child(9)")).click();
        assertThat(driver.findElement(By.cssSelector("p:nth-child(9)")).getText(), containsString("NH3 ->"));
        driver.findElement(By.cssSelector("p:nth-child(6)")).click();
        driver.findElement(By.cssSelector(".col-sm-4 > h3")).click();
        assertThat(driver.findElement(By.cssSelector(".col-sm-4 > h3")).getText(), containsString("The main pollutant was"));
        driver.findElement(By.cssSelector("p:nth-child(5)")).click();
    }
    @Test
    @Order(2)
    void cacheTest2() {
        driver.get("http://localhost:8080/api/statistics");
        driver.manage().window().setSize(new Dimension(1297, 741));
        driver.findElement(By.cssSelector("pre")).click();
        assertThat(driver.findElement(By.cssSelector("pre")).getText(), is("{\"Count of Failed Requests to Cache\":1,\"Count of Total Requests made to Cache\":2,\"Size of Cache at the moment\":1,\"Count of Successful Requests to Cache\":1}"));
        driver.findElement(By.cssSelector("html")).click();
        driver.findElement(By.cssSelector("pre")).click();
        driver.findElement(By.cssSelector("pre")).click();
        assertThat(driver.findElement(By.cssSelector("pre")).getText(), is("{\"Count of Failed Requests to Cache\":1,\"Count of Total Requests made to Cache\":2,\"Size of Cache at the moment\":1,\"Count of Successful Requests to Cache\":1}"));
        driver.findElement(By.cssSelector("html")).click();
    }
    @Test
    @Order(2)
    void websiteTest2() {
        driver.get("http://localhost:8080/airquality/index");
        driver.manage().window().setSize(new Dimension(1297, 741));
        assertThat(driver.findElement(By.cssSelector("a")).getText(), is("TQS-Homework"));
        driver.findElement(By.id("city")).click();
        driver.findElement(By.id("city")).sendKeys("Lisbon");
        driver.findElement(By.cssSelector("p:nth-child(2)")).click();
        driver.findElement(By.cssSelector(".btn")).click();
    }
    @Test
    @Order(3)
    void cacheTest3() {
        driver.get("http://localhost:8080/api/statistics");
        driver.manage().window().setSize(new Dimension(1297, 741));
        driver.findElement(By.cssSelector("pre")).click();
        driver.findElement(By.cssSelector("html")).click();
        driver.findElement(By.cssSelector("pre")).click();
        assertThat(driver.findElement(By.cssSelector("pre")).getText(), is("{\"Count of Failed Requests to Cache\":1,\"Count of Total Requests made to Cache\":2,\"Size of Cache at the moment\":1,\"Count of Successful Requests to Cache\":1}"));
        driver.findElement(By.cssSelector("html")).click();
        driver.findElement(By.cssSelector("pre")).click();
        driver.findElement(By.cssSelector("pre")).click();
        assertThat(driver.findElement(By.cssSelector("pre")).getText(), is("{\"Count of Failed Requests to Cache\":1,\"Count of Total Requests made to Cache\":2,\"Size of Cache at the moment\":1,\"Count of Successful Requests to Cache\":1}"));
        driver.findElement(By.cssSelector("html")).click();
        driver.findElement(By.cssSelector("pre")).click();
        driver.findElement(By.cssSelector("pre")).click();
        assertThat(driver.findElement(By.cssSelector("pre")).getText(), is("{\"Count of Failed Requests to Cache\":1,\"Count of Total Requests made to Cache\":2,\"Size of Cache at the moment\":1,\"Count of Successful Requests to Cache\":1}"));
        driver.findElement(By.cssSelector("html")).click();
    }
}