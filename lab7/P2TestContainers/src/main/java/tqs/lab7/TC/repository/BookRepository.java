package tqs.lab7.TC.repository;

import tqs.lab7.TC.entity.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * BookRepository
 */
@Repository
public interface BookRepository  extends JpaRepository<Book,Long>{
    List<Book> findByAuthor(String author);
    List<Book> findAll();
}