package com.dub.frontendsvr.domain;

import org.springframework.security.core.GrantedAuthority;

public class UserAuthority implements GrantedAuthority {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6938504185873553448L;
	
	private String authority;

    public UserAuthority() { }

    public UserAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
    
}