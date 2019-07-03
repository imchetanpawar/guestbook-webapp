package com.gb.gbd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gb.gbd.entity.GuestbookUser;

@Repository
public interface GuestBookUserRepository extends JpaRepository<GuestbookUser, Integer> {
	
}

