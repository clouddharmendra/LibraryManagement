package com.fsd.onlinelibrarymanagement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "User")
public class User {

	@Id
	private String id;

	private String userName;

	@Indexed(unique = true)
	private String emailId;
	private String phoneNumber;

	public User() {

	}

	public User(String userName, String emailId, String phoneNumber) {

		this.userName = userName;
		this.emailId = emailId;
		this.phoneNumber = phoneNumber;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	@Override
	public String toString() {
		return "User{" + "id='" + id + '\'' + ", userName='" + userName + '\'' + ", emailId='" + emailId + '\''
				+ ", phoneNumber=" + phoneNumber + '}';
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
