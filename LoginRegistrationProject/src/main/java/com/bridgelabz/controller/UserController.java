package com.bridgelabz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bridgelabz.dao.UserDao;
import com.bridgelabz.model.User;
import com.bridgelabz.validator.ValidationImpl;

/**
 * @author Deepak Prajapati
 * @Description this class controls all the transaction of project.
 *
 */
@Controller
public class UserController {
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private ValidationImpl validation;
	
	@Autowired
	private LoginController loginController;
	
	@RequestMapping(value="/loginform")
	public ModelAndView loginform()
	{
		return new ModelAndView("login", "command", new User());
	}
	
	@RequestMapping(value="/Userlogin", method=RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("Login") User user)
	{
		boolean islogin=loginController.getUser(user);
		if(islogin){
			return new ModelAndView("profile", "command", new User());
		}
		else{
			String message="Username password mismatch";
			return new ModelAndView("login", "message", message);
		}
	}
	
	@RequestMapping("/registrationform")
	public ModelAndView registrationForm()
	{
		return new ModelAndView("registration", "command", new User());
	}
	
	@RequestMapping(value = "/add", method=RequestMethod.POST)
	public ModelAndView addUser(@ModelAttribute("registrationform") User user) {

		String message=validation.isUserValid(user);
		if(message==null){
			boolean isUserDuplicate=userDao.duplicateUser(user);
			if(!isUserDuplicate)
			{
				String encryptedPassword=BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
				user.setPassword(encryptedPassword);
				boolean isSave=userDao.add(user);
				if(isSave){
					return new ModelAndView("/regisSuccess");
				}
				else{
					message="Registration is not completed becouse Duplicate Entry.";
					return new ModelAndView("registration", "message", message);
				}
			}
			else
			{
				message="Username and phone number is already exist.";
				return new ModelAndView("registration", "message", message);
			}
		}
		else
		{
			return new ModelAndView("registration", "message", message);
		}
	}

}
