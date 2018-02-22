package com.dub.frontendsvr.clients;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dub.frontendsvr.domain.MyUser;

@FeignClient("users-service")
public interface UserFeignClient {

	
	// get user by username
    @RequestMapping(
            method= RequestMethod.GET,
            //value="/user/{username}",
            value="${userUriPath}",
            consumes="application/json")
    ResponseEntity<MyUser> getUser(@PathVariable("username") String username);
    
	
	
}