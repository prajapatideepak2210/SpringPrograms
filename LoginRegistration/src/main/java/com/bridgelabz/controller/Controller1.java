package com.bridgelabz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bridgelabz.model.UserRegistration;

@Controller
public class Controller1 {
	@Autowired
	UserRegistration register;

	@RequestMapping(value = "/Login")
	public ModelAndView showlogin() {
		String message = "Login page";  
		return new ModelAndView("Login", "message", message);
	}
	
	@RequestMapping(value = "/Registration")
	public ModelAndView showregistration() {
		String message = "Registration Page";  
		return new ModelAndView("registration", "message", message);
	}
}
