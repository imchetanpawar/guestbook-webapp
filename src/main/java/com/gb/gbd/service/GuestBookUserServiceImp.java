package com.gb.gbd.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gb.gbd.entity.GuestbookUser;
import com.gb.gbd.entity.GuestbookUserData;
import com.gb.gbd.repository.GuestBookUserDataRepository;
import com.gb.gbd.repository.GuestBookUserRepository;

@Service
public class GuestBookUserServiceImp implements GuestBookUserService{
	
	@Autowired
	GuestBookUserRepository guestBookUserRepository;
	
	@Autowired
	GuestBookUserDataRepository guestBookUserDataRepository;
	
	/** 
	 * This method is used to save new user.
	 * @param GuestbookUser this is an entity to save the user
	*/	
	@Override
	public void saveUser(GuestbookUser user) {
		guestBookUserRepository.saveAndFlush(user);
	}

	
	/** 
	 * This method is used to save user data.
	 * @param GuestbookUserData this is an entity to save the user data
	*/	
	@Override
	public void saveUserData(GuestbookUserData userDataModel) {
		guestBookUserDataRepository.saveAndFlush(userDataModel);
	}
	
}
