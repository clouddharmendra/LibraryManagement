package com.fsd.onlinelibrarymanagement.repository;

import com.fsd.onlinelibrarymanagement.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

	public User findByEmailId(String emailId);

}
