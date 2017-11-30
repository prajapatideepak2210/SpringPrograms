package com.bridgelabz.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.bridgelabz.connection.FaceBookConnection;
import com.bridgelabz.model.Response;
import com.bridgelabz.model.User;
import com.bridgelabz.services.Service;
import com.bridgelabz.token.TokenGenerator;
import com.fasterxml.jackson.databind.JsonNode;

@RestController
public class FaceBookController {
	
	@Autowired
	Service serviceImpl;
	
	@RequestMapping(value="/facebookLogin")
	public void facebookConnection(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		String uuid = UUID.randomUUID().toString();
		request.getSession().setAttribute("State", uuid);
		String facebookLoginURL = FaceBookConnection.getFacebookURL(uuid);
		response.sendRedirect(facebookLoginURL);
	}
	
	@RequestMapping(value="/connectFB")
	public ResponseEntity<Response> redirectURL(HttpServletRequest request,HttpServletResponse response,HttpSession session,UriComponentsBuilder ucBuilder) throws IOException
	{
		Response errorMessage = new Response();
		String sessionState = (String) request.getSession().getAttribute("State");
		String googlestate = request.getParameter("state");
		
		if(sessionState==null || !sessionState.equals(googlestate))
		{
			response.sendRedirect("facebookLogin");
		}

		String error = request.getParameter("error");
		if (error != null && error.trim().isEmpty()) {
			errorMessage.setMessage("Error occured Try again.");
			return new ResponseEntity<Response>(errorMessage, HttpStatus.BAD_REQUEST);
		}
		
		String authCode = request.getParameter("code");
		String fbAccessToken = FaceBookConnection.getAccessToken(authCode);
		JsonNode profile = FaceBookConnection.getUserProfile(fbAccessToken);
		
		String email= profile.get("email").asText();
		
		User user = serviceImpl.getUserByEmail(email);
		
		if(user==null) {
			user = new User();
			user.setfName(profile.get("name").asText());
			user.setUserName(profile.get("email").asText());
			user.setPassword("");
			user.setIsUserActive(1);
			int userId=serviceImpl.addSocialUser(user);
			System.out.println("This is new user whose id : "+userId);
			if(userId>0) {
				String token = TokenGenerator.generateToken(user.getId(), user);
    			response.setHeader("Authorization", token);
    			session.setAttribute("token", token);
    			errorMessage.setMessage("User Successfully registered.");
    			return new ResponseEntity<Response>(errorMessage, HttpStatus.ACCEPTED);
 			}
			else
			{
				errorMessage.setMessage("User is not registered.");
    			return new ResponseEntity<Response>(errorMessage, HttpStatus.BAD_REQUEST);
			}
		}else {
			
			String token = TokenGenerator.generateToken(user.getId(), user);
			serviceImpl.updateUser(user);
			session.setAttribute("token", token);
			errorMessage.setMessage("User already exist.");
			return new ResponseEntity<Response>(errorMessage, HttpStatus.ALREADY_REPORTED);
		}
	}
}