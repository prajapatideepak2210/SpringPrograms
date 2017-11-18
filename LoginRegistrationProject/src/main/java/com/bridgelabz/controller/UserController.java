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
		//ModelAndView view = new ModelAndView();
		boolean islogin=loginController.getUser(user);
		System.out.println(islogin);
		if(islogin){
			return new ModelAndView("profile", "command", new User());
			/*view.addObject("name",user.getfName());
			view.setViewName("profile");*/
			//return view;
		}
		else{
			String message="Username password mismatch";
			return new ModelAndView("login", "message", message);
			/*view.addObject("message",message);
			view.setViewName("login");*/
			//return view;
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
			System.out.println("IsUserDuplicate : "+isUserDuplicate);
			if(!isUserDuplicate)
			{
				String encryptedPassword=BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
				System.out.println("Encrypted password : "+encryptedPassword);
				user.setPassword(encryptedPassword);
				boolean isSave=userDao.add(user);
				System.out.println(isSave);
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
