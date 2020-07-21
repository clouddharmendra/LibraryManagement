package com.fsd.onlinelibrarymanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsd.onlinelibrarymanagement.exception.ResourceNotFoundException;
import com.fsd.onlinelibrarymanagement.model.User;
import com.fsd.onlinelibrarymanagement.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public User getUserByEmailId(String emailId) throws ResourceNotFoundException {
		User user = userRepository.findByEmailId(emailId);
		if (user != null) {
			return user;
		} else {
			throw new ResourceNotFoundException("User not found for this emailId :: " + emailId);
		}
	}
}
