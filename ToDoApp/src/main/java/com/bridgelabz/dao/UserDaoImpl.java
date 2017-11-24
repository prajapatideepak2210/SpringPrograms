package com.bridgelabz.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.bridgelabz.model.Note;
import com.bridgelabz.model.User;

/**
 * @author Deepak Prajapati
 * @Description This class is used to control all database operation.
 *
 */
public class UserDaoImpl{
	
	@Autowired
	SessionFactory factory;
	
	/**
	 * @param user
	 * @return boolean
	 * @Description This method is used to add the users into the database. 
	 * It returns true if data is inserted, otherwise it returns false.
	 */
	public boolean add(User user)
	{
		if(user!=null){
			try{
				Session session= factory.openSession();
				Transaction transaction=session.beginTransaction();
				session.save(user);
				transaction.commit();
				session.close();
				return true;
			}catch(Exception e){
				return false;
			}
		}
		return false;
		
	}
	
	/**
	 * @return List
	 * @Description this method is used to get the list of user from the database.
	 */
	@SuppressWarnings("unchecked")
	public List<User> getUser()
	{
		Session session=factory.openSession();
		Query<User> query = session.createQuery("from User");
		List<User> list = query.list();
		session.close();
		return list;
	}
	
	
	/**
	 * @param userId
	 * @return User
	 * 
	 * @Description It will return User if user available in database otherwise return null.
	 */
	@SuppressWarnings("rawtypes")
	public User getUserById(int userId)
	{
		try {
			Session session = factory.openSession();
			Query query = session.createQuery("from User where id = :id");
			query.setParameter("id", userId);
			User user = (User) query.uniqueResult();
			session.close();
			return user;
		} catch (Exception e) {
			return null;
		}
		
	}
	
	/**
	 * @param user
	 * @return booelan
	 * @Description This method is used to check user is duplicate or not.
	 * If user is duplicate than it returns true otherwise false.
	 */
	public boolean duplicateUser(User user) {
		List<User> list=getUser();
		Iterator<User> iterator=list.iterator();
		while (iterator.hasNext()) {
			User userForDuplicate= (User) iterator.next();
			if(user.getContactNumber().equals(userForDuplicate.getContactNumber()) ||
					user.getUserName().equals(userForDuplicate.getUserName()))
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @param user
	 * @return boolean 
	 * @Description this method is used to activate the user, 
	 * it will return true if user is activated otherwise returns false.
	 */
	public boolean activateUser(User user)
	{
		try {
			Session session = factory.openSession();
			Transaction transaction=session.beginTransaction();
			session.update(user);
			transaction.commit();
			session.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
