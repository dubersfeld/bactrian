package com.dub.frontendsvr.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import com.dub.frontendsvr.controller.UserPrincipal;
import com.dub.frontendsvr.domain.MyUser;
import com.dub.frontendsvr.domain.UserAuthority;


@Service
public class UserServiceImpl implements UserService {
		
	@Value("${usersResourceUriBase}")
	String resourceUriBase;
					

	RestOperations restTemplate = new RestTemplate();

	@Override
	public UserDetails loadUserByUsername(String username) {
			
		MyUser user =
		        restTemplate.getForObject(
		        					resourceUriBase + "/" + username, 
		        					MyUser.class);
		Set<UserAuthority> auths = user.getAuthorities();
		
		for (UserAuthority auth : auths) {
			System.out.println(auth.getAuthority());
			
		}
		            
		UserPrincipal principal = new UserPrincipal(user);
	    	
	    // make sure the authorities and password are loaded
	    principal.getAuthorities().size();
	    principal.getPassword();
	    return principal;
	}
}
