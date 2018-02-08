package com.dub.users.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dub.users.domain.MyUser;
import com.dub.users.repositories.MyUserRepository;

@Service
public class MyUserServiceImpl implements MyUserService {

	@Autowired
	private MyUserRepository myUserRepository;
	
	@Override
	public MyUser loadByUsername(String username) {
		return myUserRepository.getByUsername(username);
	}

}
