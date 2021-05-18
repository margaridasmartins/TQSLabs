package tqs.lab7.TC.entity;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.processing.Generated;
import javax.persistence.*;
/**
 * Book
 */
@Entity
@Data
@Table(name="books")
public class Book {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="title")
    private String title;

    @Column(name="author")
    private String author;

    @Column(name="published")
    private LocalDate publishedDate;

    public Book(String title, String author, LocalDate publishedDate){
        this.title=title;
        this.author=author;
        this.publishedDate=publishedDate;
    }
    public Book(){}


}