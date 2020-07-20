package com.fsd.onlinelibrarymanagement.controller;

import com.fsd.onlinelibrarymanagement.exception.ResourceNotFoundException;
import com.fsd.onlinelibrarymanagement.model.SelectedBooks;
import com.fsd.onlinelibrarymanagement.repository.SelectedBooksRepository;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/library")
public class MyBooksController {
	private static final Logger logger = LoggerFactory.getLogger(MyBooksController.class);

	@Autowired
	private SelectedBooksRepository selectedBooksRepository;

	@GetMapping("/mybooks/{emailId}")
	public ResponseEntity<SelectedBooks> getBooksMyEmailId(@Valid @PathVariable String emailId)
			throws ResourceNotFoundException {
		SelectedBooks selectedBooks = this.selectedBooksRepository.findByEmailId(emailId);
		if (selectedBooks != null && selectedBooks.getMyBooks() != null && !selectedBooks.getMyBooks().isEmpty()) {
			return new ResponseEntity<SelectedBooks>(selectedBooks, HttpStatus.OK);
		}
		throw new ResourceNotFoundException("Books Not found for User email id");
	}

	@PostMapping("/saveBooks")
	public ResponseEntity<SelectedBooks> saveBooks(@Valid @RequestBody SelectedBooks selectedBooks) {
		selectedBooksRepository.save(selectedBooks);
		return new ResponseEntity<>(selectedBooks, HttpStatus.OK);
	}

	@PostMapping("/updatebooks")
	public ResponseEntity<SelectedBooks> updateSelectedBooks(@Valid @RequestBody SelectedBooks selectedBooks)
			throws ResourceNotFoundException {
		logger.info("MyBooksController ::: updateSelectedBooks():::");
		SelectedBooks dbBooks = selectedBooksRepository.findByEmailId(selectedBooks.getEmailId());
		if (dbBooks == null) {
			dbBooks = new SelectedBooks();
			dbBooks.setEmailId(selectedBooks.getEmailId());
			dbBooks.setMyBooks(selectedBooks.getMyBooks());
		} else {
			dbBooks.getMyBooks().clear();
			dbBooks.getMyBooks().addAll(selectedBooks.getMyBooks());
		}
		SelectedBooks updatedBooks = selectedBooksRepository.save(dbBooks);
		return new ResponseEntity<>(updatedBooks, HttpStatus.OK);
	}
	
	@GetMapping("/mybooks/clear")
	public String clearData() throws ResourceNotFoundException {
		try {
			this.selectedBooksRepository.deleteAll();
			return "MyBooks information cleared from Database.";
		} catch (Exception e) {
			throw new ResourceNotFoundException("Error on clearing Data.");
		}
	}
}
