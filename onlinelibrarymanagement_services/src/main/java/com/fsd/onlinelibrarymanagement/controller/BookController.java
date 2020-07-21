package com.fsd.onlinelibrarymanagement.controller;

import com.fsd.onlinelibrarymanagement.exception.ResourceNotFoundException;
import com.fsd.onlinelibrarymanagement.model.Book;
import com.fsd.onlinelibrarymanagement.repository.BooksRepository;
import com.fsd.onlinelibrarymanagement.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/library")
public class BookController {
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);
	@Autowired
	private BooksRepository booksRepository;

	@Autowired
	private UserService userService;

	@GetMapping("/books/{emailId}")
	public List<Book> getBooks(@Valid @PathVariable String emailId) throws ResourceNotFoundException {
		logger.info("BookController:::: getBooks() method executed");
		userService.getUserByEmailId(emailId);
		return booksRepository.findAll();
	}
}
