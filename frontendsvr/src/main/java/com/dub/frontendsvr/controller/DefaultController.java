package com.dub.frontendsvr.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DefaultController {
	
	@Autowired
	SessionRegistry sessionRegistry;
	
	@RequestMapping(value = {"/", "/backHome", "/index"}, 
			method = RequestMethod.GET)
	public String home() {
		
		return "index";
	}
	
	
	@GetMapping("/login")
    public ModelAndView login(ModelAndView modelAndView) {
    	
    	if(SecurityContextHolder.getContext().getAuthentication() instanceof
                UsernamePasswordAuthenticationToken) {
    		System.out.println("redirect");
    		modelAndView.setViewName("index");
            return modelAndView;
    	}
    	
    	System.out.println("number: " +  sessionRegistry.getAllPrincipals().size());
    	
    	modelAndView.addObject("number", sessionRegistry.getAllPrincipals().size());
    	modelAndView.addObject("users", sessionRegistry.getAllPrincipals());  
    
    	modelAndView.setViewName("login");
    	return modelAndView;
	}
	
	
	@GetMapping(value="/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();    	
    	if (auth != null) { 
            new SecurityContextLogoutHandler().logout(request, response, auth);
    	}

        return "redirect:/login?logout";
	}
	
}
