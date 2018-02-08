package com.dub.users.services;

import com.dub.users.domain.MyUser;

public interface MyUserService {
	
	MyUser loadByUsername(String usename);

}
