package com.bridgelabz.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bridgelabz.dao.BookDaoImpl;
import com.bridgelabz.model.Book;

public class ServicesImpl implements Services {
	
	@Autowired
	Book bookModel;
	
	@Autowired
	BookDaoImpl bookDaoImpl;

	public boolean addItem(Book book) {
		return bookDaoImpl.addBook(book);
	}

	public boolean deleteItem(int bookId) {
		bookModel.setId(bookId);
		return bookDaoImpl.deleteBook(bookModel);
	}

	public boolean updateItem(Book book) {
		return bookDaoImpl.updateBook(book);
	}

	public List<Book> getAllItems() {
		return bookDaoImpl.getBook();
	}
}
