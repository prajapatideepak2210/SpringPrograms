package com.bridgelabz.services;

import java.util.List;

import com.bridgelabz.model.Book;

public interface Services {
	public boolean addItem(Book book);
	
	public boolean deleteItem(int bookId);
	
	public boolean updateItem(Book book);
	
	public List<Book> getAllItems();
}
