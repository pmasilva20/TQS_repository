package io.github.bonigarcia;

import java.util.NoSuchElementException;

import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SearchSteps {

    private WebDriver webDriver;


    @When("I navigate to {string}")
    public void iNavigateTo(String url) {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get(url);
    }

    @And("I type {string}")
    public void iType(String searchQuery) {
        webDriver.findElement(By.name("q")).sendKeys(searchQuery);
    }

    @And("I press Enter")
    public void iPressEnter() {
        webDriver.findElement(By.name("q")).sendKeys(Keys.ENTER);
    }

    @Then("I should be shown results including {string}")
    public void iShouldBeShownResultsIncluding(String result) {
        try {
            webDriver.findElement(
                    By.xpath("//*[contains(text(), '" + result + "')]"));
        } catch (NoSuchElementException e) {
            throw new AssertionError(
                    "\"" + result + "\" not available in results");
        } finally {
            webDriver.quit();
        }
    }

    @And("^I choose (\\w+) as my (\\w+)")
    public void iChoosePlace(String place,String mode) {
        WebElement dropdown;
        if(mode.equals("departure")){
            dropdown = webDriver.findElement(By.name("fromPort"));
        }
        else{
            dropdown = webDriver.findElement(By.name("toPort"));
        }
        dropdown.findElement(By.xpath("//option[. = '"+place+"']")).click();
    }

    @And("I press ([\\w\\s]+)")
    public void iPressButton() {
        webDriver.findElement(By.cssSelector(".btn-primary")).click();
    }


    @When("I select a flight")
    public void iSelectAFlight() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get("https://blazedemo.com/reserve.php");

        webDriver.findElement(By.cssSelector(".btn-primary")).click();
    }

    @When("^I enter my ([\\w\\s]+)$")
    public void iEnterMyInfo(String info) {
        String idName = "";
        String input = "";
        switch (info){
            case "name":
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver();
                webDriver.get("https://blazedemo.com/purchase.php");

                idName = "inputName";
                input = "Martim";
                break;
            case  "address":
                idName = "address";
                input = "12345 Street Name";
                break;
            case  "city":
                idName = "city";
                input = "ATown";
                break;
            case  "state":
                idName = "state";
                input = "AState";
                break;
            case  "zip code":
                idName = "zipCode";
                input = "12345";
                break;
            case  "credit card number":
                idName = "creditCardNumber";
                input = "123123123";
                break;
            case  "name on card":
                idName = "nameOnCard";
                input = "Martim Martim";
                break;
        }
        webDriver.findElement(By.id(idName)).click();
        webDriver.findElement(By.id(idName)).sendKeys(input);

    }

    @And("I choose my card type")
    public void iChooseMyCardType() {
        webDriver.findElement(By.cssSelector(".control-group:nth-child(7)")).click();
    }


}
