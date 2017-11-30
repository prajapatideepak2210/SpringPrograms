package com.bridgelabz.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.model.PasswordUser;
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

		Response response = new Response();
		String message = Validator.isUserValid(user);
		String url = request.getRequestURL().toString();
		if (message == null) {
			User checkUser=serviceImpl.add(user, url);
			if (checkUser!=null) {

				response.setMessage(
						"User Successfully Registered, user got a mail for verifing please go there and verify.");
				return new ResponseEntity<Response>(response, HttpStatus.CREATED);
			}
			response.setMessage("User Already Exist.");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		} else {
			response.setMessage(message);
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> login(@RequestBody User user, HttpSession session) {

		if (serviceImpl.isUserAvailable(user)) {
			String userName = serviceImpl.login(user);
			if (userName != null) {
				Response response = new Response();
				session.setAttribute("user", userName);
				response.setMessage("User SuccessFully Logedin.");
				return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);
			} else {
				Response response = new Response();
				response.setMessage("UserName and Password Mismatch.");
				return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);
			}
		} else {
			Response response = new Response();
			response.setMessage("User is not alvailable, please register first.");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ResponseEntity<Response> logout(HttpSession session) {
		Response response = new Response();
		session.removeAttribute("user");
		session.invalidate();
		response.setMessage("Logout Successfully.");
		return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);
	}


	@RequestMapping(value = "/active/{jwt:.+}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> verifyToken(@PathVariable("jwt") String token) {
		int id = TokenGenerator.verifyToken(token);
		Response response = new Response();
		if (id != 0) {
			User user = serviceImpl.getUserById(id);
			if (user != null) {
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

	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> forgotPassword(@RequestBody User user, HttpServletRequest request) {
		Response response = new Response();
		if (user.getUserName() != null) {
			String url = request.getRequestURL().toString();
			User users = serviceImpl.getUserByEmail(user.getUserName());
			boolean isSent = serviceImpl.forgotPassword(users, url);
			if (isSent) {
				response.setMessage("Go on email, and verify the link.");
				return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);
			}
			response.setMessage("fill again the email and click on forgot password.");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		}
		response.setMessage("email is not filled, please fill the email and click on forgot password.");
		return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
	}

	
	@RequestMapping(value = "/verifyUser/{jwt:.+}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> userVerify(@PathVariable("jwt") String token) {
		int id = TokenGenerator.verifyToken(token);
		Response response = new Response();
		if (id != 0) {
			User user = serviceImpl.getUserById(id);
			if (user != null) {
				response.setMessage(
						"You are verified Your key is " + user.getId() + " please reset your password with this key.");
				return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);
			}
			response.setMessage("User Not Found");
			return new ResponseEntity<Response>(response, HttpStatus.NOT_FOUND);
		}
		response.setMessage("you are not user.");
		return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value="/resetPassword", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> resetPassword(@RequestBody PasswordUser passwordUser)
	{
		Response response=new Response();
		if(passwordUser.getPassword().equals(passwordUser.getConfirmPassword()))
		{
			int id=passwordUser.getId();
			User user=serviceImpl.getUserById(id);
			String securePassword=BCrypt.hashpw(passwordUser.getPassword(), BCrypt.gensalt());
			user.setPassword(securePassword);
			User checkUser=serviceImpl.updateUser(user);
			if(checkUser!=null)
			{
				response.setMessage("Password Reset Successfully.");
				return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);
			}
			response.setMessage("Request not submited.");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		}
		response.setMessage("Password and confirmpassword is not matching, please reset again.");
		return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
	}
}
