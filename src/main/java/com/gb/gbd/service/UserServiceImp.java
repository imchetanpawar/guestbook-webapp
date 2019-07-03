package com.gb.gbd.service;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gb.gbd.repository.GuestBookUserSecurityRepository;
import com.gb.gbd.repository.UserRepository;

@Service
public class UserServiceImp implements UserService{

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	GuestBookUserSecurityRepository guestBookUserSecurityRepository;

	/**
	 * This method returns boolean value as per user check in table.
	*/
	@Override
	public boolean isUserExists(String userName) {
		boolean isPresent=userRepo.findByUserName(userName).isPresent();
		return isPresent;
	}
	
}
