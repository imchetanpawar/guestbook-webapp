package com.gb.gbd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gb.gbd.entity.GuestbookUserSecurity;


/**
 * This is repository for GuestbookUserSecurity in which new user data password has been saved in encrypted format. 
*/
@Repository
public interface GuestBookUserSecurityRepository extends JpaRepository<GuestbookUserSecurity, Integer> {
	
	
	
}

