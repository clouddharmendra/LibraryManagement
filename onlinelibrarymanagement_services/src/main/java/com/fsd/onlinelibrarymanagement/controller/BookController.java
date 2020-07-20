package com.fsd.onlinelibrarymanagement.controller;

import com.fsd.onlinelibrarymanagement.model.Book;
import com.fsd.onlinelibrarymanagement.repository.BooksRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/library")
public class BookController {
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);
	@Autowired
	private BooksRepository booksRepository;

	@GetMapping("/books")
	public List<Book> getBooks() {
		logger.info("BookController:::: getBooks() method executed");
//		System.out.println(System.getProperty("java.io.tmpdir"));
		return booksRepository.findAll();
	}
}
