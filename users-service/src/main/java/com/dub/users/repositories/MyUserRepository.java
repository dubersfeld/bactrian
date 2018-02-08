package com.dub.users.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dub.users.domain.MyUser;

public interface MyUserRepository extends JpaRepository<MyUser, Long> {

	MyUser getByUsername(String username);
	
}
