package com.bridgelabz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.model.MailUser;
import com.bridgelabz.model.Response;
import com.bridgelabz.model.User;
import com.bridgelabz.services.Service;
import com.bridgelabz.validator.Validator;

/**
 * @author Deepak Prajapati
 * @Description this class controls all the transaction of project.
 *
 */
@RestController
public class UserController {
	@Autowired
	Service serviceImpl;


	@RequestMapping(value = "/getUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getUser() {
		List<User> list = serviceImpl.getUser();
		return list;
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public ResponseEntity<Response> addUser(@RequestBody User user) {

		Response response=new Response();
		String message = Validator.isUserValid(user);
		
		if (message == null) {
			if (serviceImpl.add(user)) {
				
				response.setMessage("User Successfully Registered");
				return new ResponseEntity<Response>(response, HttpStatus.CREATED);
			}
			response.setMessage("User Already Exist.");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		}
		else {
			response.setMessage(message);
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> login(@RequestBody User user) {

		if(serviceImpl.isUserAvailable(user))
		{
			if(serviceImpl.login(user))
			{
				Response response=new Response();
				response.setMessage("User SuccessFully Logedin.");
				return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);
			}
			else{
				Response response=new Response();
				response.setMessage("UserName and Password Mismatch.");
				return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);
			}
		}
		else{
			Response response=new Response();
			response.setMessage("User is not alvailable, please register first.");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/mail", method=RequestMethod.POST)
	public ResponseEntity<Response> email(@RequestBody MailUser mailUser)
	{
		if(serviceImpl.sendMail(mailUser))
		{
			Response response=new Response();
			response.setMessage("User SuccessFully sent.");
			return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);
		}   
		else{
			Response response=new Response();
			response.setMessage("Mail not sent.");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		}
		
	}
}
