package com.bridgelabz.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.bridgelabz.model.User;

/**
 * @author Deepak Prajapati
 * @Description This class is used to control all database operation.
 *
 */
public class UserDao {
	
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
		boolean isAdd=false;
		if(user!=null){
			try{
				Session session= factory.openSession();
				Transaction transaction=session.beginTransaction();
				session.save(user);
				transaction.commit();
				session.close();
				isAdd=true;
				return isAdd;
			}catch(Exception e)
			{
				return false;
			}
		}
		else{
			return false;
		}
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
}
