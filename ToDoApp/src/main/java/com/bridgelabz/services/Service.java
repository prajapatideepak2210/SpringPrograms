package com.bridgelabz.services;

import java.util.List;

import com.bridgelabz.model.MailUser;
import com.bridgelabz.model.User;

public interface Service {
	boolean login(User user);
	boolean add(User user);
	public List<User> getUser();
	boolean isUserAvailable(User user);
	boolean sendMail(MailUser mailUser);
}
