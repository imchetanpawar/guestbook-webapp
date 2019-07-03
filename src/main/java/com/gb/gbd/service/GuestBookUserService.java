package com.gb.gbd.service;

import com.gb.gbd.entity.GuestbookUser;
import com.gb.gbd.entity.GuestbookUserData;

public interface GuestBookUserService {
    
    /**
     * 
     * @param user
     */
    void saveUser(GuestbookUser user);
    
    void saveUserData(GuestbookUserData userDataModel);
    
}
