package com.fsd.onlinelibrarymanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.fsd.onlinelibrarymanagement.model.Book;
import com.fsd.onlinelibrarymanagement.model.User;
import com.fsd.onlinelibrarymanagement.repository.BooksRepository;
import com.fsd.onlinelibrarymanagement.repository.SelectedBooksRepository;
import com.fsd.onlinelibrarymanagement.repository.UserRepository;

@SpringBootApplication
public class OnlinelibrarymanagementServicesApplication {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BooksRepository booksRepository;

	@Autowired
	private SelectedBooksRepository selectedBooksRepository;

	public static void main(String[] args) {
		SpringApplication.run(OnlinelibrarymanagementServicesApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			selectedBooksRepository.deleteAll();
			userRepository.deleteAll();
			booksRepository.deleteAll();
			addBooksAndUsersInfo();
		};
	}

	private void addBooksAndUsersInfo() {
		// Add user and Books info
		userRepository.save(new User("Dharmendra Mutyala", "dharmendra@xyz.com", "9176982345"));
		booksRepository.save(new Book(1, "Kalki", "Kalki", 100, true, "English", 2020, "Fiction", "Kevin Missal",
				"Eye of Brahma", 17, null, "Borrow", null, "Jaico Publishing House"));
		booksRepository.save(new Book(2, "A Country Year", "A Country Year", 50, true, "French", 2018, "Fiction",
				"Sue Hubbell", "Living the Questions", 18, null, "Borrow", null, "Westland Publications"));
		booksRepository.save(new Book(3, "Gandhi", "Gandhi", 150, true, "Hindi", 2018, "Autobiography", "Gandhi",
				"The Story of my experiments with truth", 15, null, "Borrow", null, "Roli Books"));
		booksRepository.save(new Book(4, "Half Girlfriend", "Half Girlfriend", 55, true, "English", 2010, "Love story",
				"Chetan Bhagat", "Half girl friend", 10, null, "Borrow", null, "Hachette India"));
		booksRepository.save(new Book(5, "How To Impress A Girl", "How To Impress A Girl", 143, true, "English", 2012,
				"Socio fantasy", "Rick Manning", "A Powerful step-by-step guide", 9, null, "Borrow", null,
				"Scholastic India"));
		booksRepository.save(new Book(6, "How to Love Yourself", "How to Love Yourself", 150, true, "English", 2020,
				"Fiction", "Lakeysha", "A guide to building your self-esteem when you don't know where to start", 1,
				null, "Borrow", null, "Roli Books"));
		booksRepository.save(new Book(7, "Camino Winds", "John Grisham", 40, true, "German", 2009, "Novel",
				"John Grisham", "John Grisham Camino Winds", 12, null, "Borrow", null, "24by7Publishing"));
		booksRepository.save(new Book(8, "Nature", "Nature", 40, true, "English", 1987, "Novel", "Nature",
				"My first book about Nature", 16, null, "Borrow", null, "Pothi"));
		booksRepository.save(new Book(9, "The 5 Love Languages", "The 5 Love Languages", 25, true, "Hindi", 2017,
				"Novel", "Gary Champman", "The secret to love that lasts", 16, null, "Borrow", null,
				"Rupa Publications"));
	}
}
