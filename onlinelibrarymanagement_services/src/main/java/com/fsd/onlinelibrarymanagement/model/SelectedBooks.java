package com.fsd.onlinelibrarymanagement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "SelectedBooks")
public class SelectedBooks {

	@Transient
	public static final String SEQUENCE_NAME = "database_sequences";

	@Id
	private long id;

	private String emailId;
	private List<Book> myBooks;

	public List<Book> getMyBooks() {
		if (myBooks == null) {
			myBooks = new ArrayList<Book>();
		}
		return myBooks;
	}

	public void setMyBooks(List<Book> myBooks) {
		if (myBooks == null) {
			myBooks = new ArrayList<Book>();
		}
		this.myBooks = myBooks;
	}

	public static String getSequenceName() {
		return SEQUENCE_NAME;
	}

	public SelectedBooks() {

	}

	public SelectedBooks(long id, String emailId, List<Book> myBooks) {
		this.id = id;
		this.emailId = emailId;
		this.myBooks = myBooks;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
}
