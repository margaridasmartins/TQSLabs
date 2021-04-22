package tqs;

import java.time.LocalDate;

public class Book {
    private final String title;
    private final String author;
    private final LocalDate published;
    
    public Book(String title, String author,LocalDate published){
        this.title=title;
        this.author=author;
        this.published=published;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public LocalDate getPublished() {
        return this.published;
    }
}