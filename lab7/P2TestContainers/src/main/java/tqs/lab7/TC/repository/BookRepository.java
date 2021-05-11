package tqs.lab7.TC.repository;

import tqs.lab7.TC.entity.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * BookRepository
 */
@Repository
public interface BookRepository  extends JpaRepository<Book,Long>{

}