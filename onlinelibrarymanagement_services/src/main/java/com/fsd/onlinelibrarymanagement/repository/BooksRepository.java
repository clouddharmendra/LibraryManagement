package com.fsd.onlinelibrarymanagement.repository;

import com.fsd.onlinelibrarymanagement.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BooksRepository extends MongoRepository<Book, String> {
	public List<Book> findAll();
}
