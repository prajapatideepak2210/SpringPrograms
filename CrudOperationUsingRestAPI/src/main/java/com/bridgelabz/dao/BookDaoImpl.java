package com.bridgelabz.dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.bridgelabz.model.Book;


public class BookDaoImpl implements BookDao {

	@Autowired
	SessionFactory factory;
	
	public boolean addBook(Book book) {
		try {
			Session session = factory.openSession();
			Transaction transaction=session.beginTransaction();
			session.save(book);
			transaction.commit();
			session.close();
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	public boolean deleteBook(Book book) {
		try {
			Session session = factory.openSession();
			Transaction transaction=session.beginTransaction();
			session.delete(book);
			transaction.commit();
			session.close();
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	public boolean updateBook(Book book) {
		try {
			Session session = factory.openSession();
			Transaction transaction=session.beginTransaction();
			session.update(book);
			transaction.commit();
			session.close();
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Book> getBook() {
		try {
			Session session = factory.openSession();
			Query<Book> query=session.createQuery("from Book");
			List<Book> list=query.list();
			return list;
		} catch (Exception e) {
			return null;
		}
	}
}
