Feature: Purchase a flight in Blazedemo
 
  Scenario: See flights
    When I navigate to "https://blazedemo.com/"
    And I choose Paris as my departure
    And I choose London as my destination
    And I press Find Flights
    Then I should be shown results including "Flights from Paris to London"


  Scenario: Choose a flight
    When I select a flight
    Then I should be shown results including "Your flight from TLV to SFO has been reserved"

    Scenario: Purchase flight
      When I enter my name
      And I enter my address
      And I enter my city
      And I enter my state
      And I enter my zip code
      And I choose my card type
      And I enter my credit card number
      And I enter my name on card
      And I press Purchase Flight
      Then I should be shown results including "Thank you for your purchase today!"
