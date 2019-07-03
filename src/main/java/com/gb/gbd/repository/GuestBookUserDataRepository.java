package com.gb.gbd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gb.gbd.entity.GuestbookUserData;

/**
 * This is repository for GuestbookUserData in which new user data has been saved. 
*/

@Repository
public interface GuestBookUserDataRepository extends JpaRepository<GuestbookUserData, Integer> {
	
	/**
	 * findByUserName: is used to retrieve all user data along with approve/ remove flag, it returns a list of guest users.
	*/
	 @Query("SELECT U FROM GuestbookUserData U"
	 ) Optional<List<GuestbookUserData>> findByUserName();
	 
	/**
	 * setApproveRemoveFlag: This method used to update approve/remove flag as per inputs provided by admin user.
	 * @param userId, approveFlag, removeFlag
	*/
	 @Modifying
	 @Query("update GuestbookUserData ear set ear.approveFlag = ?2 , ear.removeFlag = ?3 where ear.userID = ?1")
	 int setApproveRemoveFlag(int userID, String aprroveFlag, String removeFlag);
	 
}

