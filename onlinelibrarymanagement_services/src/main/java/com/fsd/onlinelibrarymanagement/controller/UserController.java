package com.fsd.onlinelibrarymanagement.controller;

import com.fsd.onlinelibrarymanagement.exception.ResourceNotFoundException;
import com.fsd.onlinelibrarymanagement.model.User;
import com.fsd.onlinelibrarymanagement.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/library")
public class UserController {

	private final UserRepository userRepository;
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/**
	 * To fetch user information using user's email Id.
	 * 
	 * @param emailId
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@GetMapping("/login/{emailId}")
	public ResponseEntity<User> getUserByEmailId(@PathVariable String emailId) throws ResourceNotFoundException {
		logger.debug(" --- login user into online shopping ---");
		User user = userRepository.findByEmailId(emailId);
		if (user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("User not found for this emailId :: " + emailId);
		}
	}

	/**
	 * To allow the new user to register into the online shopping application
	 * 
	 * @param user
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@PostMapping("/signup")
	public ResponseEntity<?> login(@RequestBody User user) throws ResourceNotFoundException {

		User dbUser = userRepository.findByEmailId(user.getEmailId());
		if (null != dbUser) {
			throw new ResourceNotFoundException("User already registered found withi emailId :: " + user.getEmailId());

		} else {
			userRepository.save(user);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
	}

	/**
	 * To update the user information such as billing address and shipping address.
	 * 
	 * @param user
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@PutMapping("/updateUserDetails")
	public ResponseEntity<?> updateUserData(@RequestBody User user) throws ResourceNotFoundException {
		User dbUser = userRepository.findByEmailId(user.getEmailId());
		if (null != dbUser) {
			userRepository.save(dbUser);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("User not found for this id :: " + user.getEmailId());
		}
	}

}
