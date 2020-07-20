package com.fsd.onlinelibrarymanagement.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Books")
public class Book {
	private int id;
	private String title;
	private String image;
	private float price;
	private boolean available;
	private String language;
	private int yearOfPublication;
	private String genre;
	private String author;
	private String description;
	private int isbn;
	private String emailId;
	private String btnText;
	private Date borrowedDate;
	private String publisher;

	public Book(int id, String title, String image, float price, boolean available, String language,
			int yearOfPublication, String genre, String author, String description, int isbn, String emailId,
			String btnText, Date borrowedDate, String publisher) {
		super();
		this.id = id;
		this.title = title;
		this.image = image;
		this.price = price;
		this.available = available;
		this.language = language;
		this.yearOfPublication = yearOfPublication;
		this.genre = genre;
		this.author = author;
		this.description = description;
		this.isbn = isbn;
		this.emailId = emailId;
		this.btnText = btnText;
		this.borrowedDate = borrowedDate;
		this.publisher = publisher;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	
	public Date getBorrowedDate() {
		return borrowedDate;
	}

	public void setBorrowedDate(Date borrowedDate) {
		this.borrowedDate = borrowedDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", image=" + image + ", price=" + price + ", available="
				+ available + ", language=" + language + ", yearOfPublication=" + yearOfPublication + ", genre=" + genre
				+ ", author=" + author + ", description=" + description + ", isbn=" + isbn + ", emailId=" + emailId
				+ ", btnText=" + btnText + ", borrowedDate=" + borrowedDate + ", publisher=" + publisher + "]";
	}

	public String getBtnText() {
		return btnText;
	}

	public void setBtnText(String btnText) {
		this.btnText = btnText;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public boolean getAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getYearOfPublication() {
		return yearOfPublication;
	}

	public void setYearOfPublication(int yearOfPublication) {
		this.yearOfPublication = yearOfPublication;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
}
