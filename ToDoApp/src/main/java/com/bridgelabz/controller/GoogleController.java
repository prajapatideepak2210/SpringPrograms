package com.bridgelabz.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.connection.GoogleConnection;
import com.bridgelabz.model.User;
import com.bridgelabz.services.Service;
import com.bridgelabz.token.TokenGenerator;
import com.fasterxml.jackson.databind.JsonNode;

@RestController
public class GoogleController {

	@Autowired
	Service userService;
	

	@RequestMapping(value = "/googleLogin")
	public void googleConnection(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println(" in googleLoginURL  ");
		String unid = UUID.randomUUID().toString();
		request.getSession().setAttribute("STATE", unid);
		String googleLoginURL = GoogleConnection.getGoogleAuthURL(unid);
		System.out.println("googleLoginURL  " + googleLoginURL);
		response.sendRedirect(googleLoginURL);
	}
	
	@RequestMapping(value="/connectgoogle")
	public void redirectFromGoogle(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String sessionState = (String) request.getSession().getAttribute("STATE");
		String googlestate = request.getParameter("state");
		
		System.out.println("in connect google");

		if (sessionState == null || !sessionState.equals(googlestate)) {
			response.sendRedirect("loginWithGoogle");
		}

		String error = request.getParameter("error");
		if (error != null && error.trim().isEmpty()) {
			response.sendRedirect("login");
		}
		
		String authCode = request.getParameter("code");
		String googleaccessToken = GoogleConnection.getAccessToken(authCode);
		System.out.println("accessToken " + googleaccessToken);
		
		JsonNode profile = GoogleConnection.getUserProfile(googleaccessToken);
		System.out.println("google profile :"+profile);
		System.out.println("google profile :" + profile.get("displayName"));
		System.out.println("user email in google login :" + profile.get("emails").get(0).get("value").asText()); //asText() is use to remove outer text.
		System.out.println("google profile :" + profile.get("image").get("url"));
		User user = userService.getUserByEmail(profile.get("emails").get(0).get("value").asText());

		if (user == null) {
			System.out.println(" user is new to our db");
			user = new User();
			user.setfName(profile.get("displayName").asText());
			user.setUserName(profile.get("emails").get(0).get("value").asText());
			user.setPassword("");
			userService.addSocialUser(user);
		}
		
		System.out.println(" user is not new to our db ,it is there in our db");
		/*tokens = tokenManupulation.generateTokens();
		user.setProfile(profile.get("image").get("url").asText());
		userService.updateUserProfile(user);
		
		tokens.setGetUser(user);
		tokenService.saveToken(tokens);*/
		user = userService.getUserByEmail(user.getUserName());
		String token = TokenGenerator.generateToken(user.getId(), user);
		response.setHeader("Authentication", token);
		System.out.println(token);
		Cookie accCookie = new Cookie("socialaccessToken", token);
		response.addCookie(accCookie);
		
		response.sendRedirect("http://localhost:8080/ToDo/#!/home");
	}
	
}