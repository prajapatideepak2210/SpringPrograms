package com.bridgelabz.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.model.Response;
import com.bridgelabz.model.User;
import com.bridgelabz.services.Service;
import com.bridgelabz.token.TokenGenerator;
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

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<Response> addUser(@RequestBody User user, HttpServletRequest request) {

		Response response=new Response();
		String message = Validator.isUserValid(user);
		String url=request.getRequestURL().toString();
		if (message == null) {
			if (serviceImpl.add(user, url)) {
				
				response.setMessage("User Successfully Registered, user got a mail for verifing please go there and verify.");
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
			if(serviceImpl.login(user)==1)
			{
				Response response=new Response();
				response.setMessage("User SuccessFully Logedin.");
				return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);
			}
			else if(serviceImpl.login(user)==0){
				Response response=new Response();
				response.setMessage("You are not verified, Register again with another email and verify the user.");
				return new ResponseEntity<Response>(response, HttpStatus.SERVICE_UNAVAILABLE);
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
	
	
	/*@RequestMapping(value="/mail", method=RequestMethod.POST)
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
		
	}*/
	
	
	@RequestMapping(value = "/active/{jwt:.+}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> verifyToken(@PathVariable("jwt") String token) {
		int id = TokenGenerator.verifyToken(token);
		Response response = new Response();
		if (id!=0) {
			User user = serviceImpl.getUserById(id);
			if (user!=null) {
				serviceImpl.activeUser(id, user);
				response.setMessage("User has been Activated");
				return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);
			}
			response.setMessage("User is not available.");
			return new ResponseEntity<Response>(response, HttpStatus.NOT_FOUND);
		}
		response.setMessage("Wrong id.");
		return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
	}
}
