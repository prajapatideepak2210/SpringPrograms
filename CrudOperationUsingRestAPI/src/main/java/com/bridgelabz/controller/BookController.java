package com.bridgelabz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.model.Book;
import com.bridgelabz.services.ServicesImpl;

@RestController
public class BookController {

	@Autowired
	ServicesImpl servicesImpl;

	@RequestMapping("/")
	public String welcome() {
		return "Welcome to RestTemplate Example.";
	}

	@RequestMapping(value = "/getBooks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Book> getBooks() {
		List<Book> list = servicesImpl.getAllItems();
		return list;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void addBook(@RequestBody Book book) {
		servicesImpl.addItem(book);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public void updateBook(@RequestBody Book book) {
		servicesImpl.updateItem(book);
	}

	@RequestMapping(value = "/delete/{bookId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteBook(@PathVariable("bookId") int bookId) {
		servicesImpl.deleteItem(bookId);
	}
}
