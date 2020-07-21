package com.fsd.onlinelibrarymanagement.controller;

import com.fsd.onlinelibrarymanagement.exception.ResourceNotFoundException;
import com.fsd.onlinelibrarymanagement.model.User;
import com.fsd.onlinelibrarymanagement.repository.UserRepository;
import com.fsd.onlinelibrarymanagement.service.UserService;

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

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

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
		User user = userService.getUserByEmailId(emailId);
		if (user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("User not found for this emailId :: " + emailId);
		}
	}
}
