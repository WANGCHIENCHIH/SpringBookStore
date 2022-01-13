package com.example.springbookstore.DAO;

import com.example.springbookstore.Entity.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
