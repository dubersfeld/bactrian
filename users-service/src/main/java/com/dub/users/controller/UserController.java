package com.dub.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dub.users.domain.MyUser;
import com.dub.users.services.MyUserService;

@RestController
public class UserController {
	
	@Value("${spring.datasource.url}")
	String url;
	
	@Autowired
	private MyUserService myUserService;
	
	@RequestMapping("/api/user/{username}")
	public MyUser getUser(@PathVariable("username") String username) {
			
		System.out.println("users-service getUser " + url);
		return myUserService.loadByUsername(username);
	}

}
