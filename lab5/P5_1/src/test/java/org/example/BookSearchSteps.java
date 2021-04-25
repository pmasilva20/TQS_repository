package org.example;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Format;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookSearchSteps {
    private final Library lib = new Library();
    List<Book> result = new ArrayList<>();

    @ParameterType("([0-9]{2}) ([0-9]{2}) ([0-9]{4})")
    public LocalDateTime europeDate(String year,String month,String day){

        return LocalDateTime.of(Integer.parseInt(day), Integer.parseInt(month),Integer.parseInt(year),0, 0);
    }

    @Given("a book with the title {string}, written by {string}, published in {europeDate}")
    public void setup(String title,
                      String author,
                      final LocalDateTime published) {
        Book book1 = new Book(title, author, published);
        lib.addBook(book1);
    }

    @And("another book with the title {string}, written by {string}, published in {europeDate}")
    public void setupAnd(
                      String title2,
                      String author2,
                      final LocalDateTime published2) {
        Book book2 = new Book(title2, author2, published2);
        lib.addBook(book2);
    }


    @When("the customer searches for books published between {int} and {int}")
    public void searchByDate(int arg1, int arg2) {
        Date d1 = new Date();
        d1.setYear(arg1);
        Date d2 = new Date();
        d2.setYear(arg2);
        result = lib.findBooksByDate(d1,d2);
    }

    @When("the costumer searches for books written by {string}")
    public void searchByAuthor(String arg1) {
        result = lib.findBooksByAuthor(arg1);
    }

    @When("the costumer searches for books with the title {string}")
    public void searchByTitle(String arg1) {
        result = lib.findBooksByTitle(arg1);
    }

    @Then("^(\\d+) book is found$")
    public void the_result_is(double expected) {
        assertEquals(expected, result.size());
    }
    @And("Book {int} should have the title {string}")
    public void the_result_is2(int index, String title) {
        assertTrue(result.get(0).getTitle().contains(title));
    }
}