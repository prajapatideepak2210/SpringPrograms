package com.bridgelabz.dao;

import java.util.List;

import com.bridgelabz.model.Book;

public interface BookDao {
	public boolean addBook(Book book);
	
	public boolean deleteBook(Book book);
	
	public boolean updateBook(Book book);
	
	public List<Book> getBook();
	
}
