package com.bridgelabz.services;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.bridgelabz.dao.UserDaoImpl;
import com.bridgelabz.mailUtility.MyMailSender;
import com.bridgelabz.model.MailUser;
import com.bridgelabz.model.User;
import com.bridgelabz.token.TokenGenerator;

public class ServiceImpl implements Service{
	
	@Autowired
	UserDaoImpl userDao;
	
	@Autowired
	MyMailSender myMailSender;
	
	public boolean login(User loginUser) {
		User user=new User();
		List<User> list=userDao.getUser();
		Iterator<User> iterator=list.iterator();
		while(iterator.hasNext())
		{
			user=iterator.next();
			
			if(user.getUserName().equals(loginUser.getUserName()) &&
					BCrypt.checkpw(loginUser.getPassword(), user.getPassword()))
			{
				return true;
			}
		}
		return false;
	}

	public boolean add(User user) {
		if(!userDao.duplicateUser(user)){
			String password=BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
			user.setPassword(password);
			return userDao.add(user);
		}
		return false;
	}
	
	public List<User> getUser()
	{
		return userDao.getUser();
	}
	
	public boolean isUserAvailable(User loginUser)
	{
		List<User> list=userDao.getUser();
		Iterator<User> iterator=list.iterator();
		User user=new User();
		while(iterator.hasNext())
		{
			user=iterator.next();
			
			if(user.getUserName().equals(loginUser.getUserName()))
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean sendMail(MailUser mailUser)
	{
		return myMailSender.sendMail(mailUser.getTo(), mailUser.getSubject(), mailUser.getMessage());
		
	}
	
	public String getToken(User user)
	{
		return TokenGenerator.generateToken(user.getId(), user);
	}
}
