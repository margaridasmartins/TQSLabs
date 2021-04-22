Feature: Book Search
    To allow a customer to find for a book fast, the library must allow him to search by author, title, or publication date

    Background:
        Given a book with the title "A Game of Thrones", written by "George RR Martin", published in "1996-08-06"
            And a book with the title "A Dance with Dragons", written by "George RR Martin", published in "2011-07-12"
            And a book with the title "Some book", written by "Some guy", published in "1996-08-07"

    Scenario: Search books by publication year
        When the customer searches for books published between "1993-04-03" and "1997-04-03"
        Then 2 books should have been found
            And Book 1 should have the title "Some book"
            And Book 2 should have the title "A Game of Thrones" 
            

    Scenario: Search books by author
        When the customer searches for books written by "George"
        Then 2 books should have been found
            And Book 1 should have the title "A Dance with Dragons"
            And Book 2 should have the title "A Game of Thrones" 
    
    Scenario: Search books by title
        When the customer searches for books with title "Dance"
        Then 1 books should have been found
            And Book 1 should have the title "A Dance with Dragons"