package com.fsd.onlinelibrarymanagement.repository;

import com.fsd.onlinelibrarymanagement.model.SelectedBooks;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SelectedBooksRepository extends MongoRepository<SelectedBooks, Long> {
	public SelectedBooks findByEmailId(String emailId);

	public List<SelectedBooks> findAll();
}
