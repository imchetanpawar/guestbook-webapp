package com.gb.gbd.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gb.gbd.entity.GuestbookUser;

/**
 * This is repository for GuestbookUser in which new user data saved / check has been done on the basis of userName. 
*/
@Repository
public interface UserRepository extends JpaRepository<GuestbookUser, Long> {

	Optional<GuestbookUser> findByUserName(String userName);
}
