Feature: Book Search
  Each costumer must be able to search a book by author, by category,
  by publication year

  Scenario: Search books by publication year
    Given a book with the title 'A book', written by 'Someone', published in 19 04 2020
    And another book with the title 'Some other book', written by 'Tim Tomson', published in 23 08 2014
    When the customer searches for books published between 2013 and 2014
    Then 1 book is found
    And Book 1 should have the title 'Some other book'

    Scenario: Search books by author
      Given a book with the title 'A book', written by 'Someone', published in 19 04 2020
      And another book with the title 'Another book', written by 'Someone else', published in 04 08 2014
      When the costumer searches for books written by 'Someone'
      Then 1 book is found
      And Book 1 should have the title 'A book'

    Scenario:Search books by title
      Given a book with the title 'A book', written by 'Someone', published in 19 04 2020
      And another book with the title 'Another book', written by 'Someone else', published in 04 08 2014
      When the costumer searches for books with the title 'A book'
      Then 1 book is found
      And Book 1 should have the title 'A book'

