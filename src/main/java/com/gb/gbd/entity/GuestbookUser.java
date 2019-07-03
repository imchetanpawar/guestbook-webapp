package com.gb.gbd.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "GUESTBOOK_USER",schema="TEST1")
public class GuestbookUser {

	@Id
	//	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "user_Sequence")
	@SequenceGenerator(name = "user_Sequence", sequenceName = "GUESTBOOK_USER_SEQ")
	@Column(name = "USER_ID")
	private int userID;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "USER_TYPE")
	private String userType;

	@Column(name = "USER_CREATED")
	private Date userCreate;

	public GuestbookUser() {
		super();
	}


	@OneToOne(fetch = FetchType.LAZY, mappedBy = "guestbookUser", cascade = CascadeType.ALL)
	private GuestbookUserDetails guestbookUserDetails;

	public GuestbookUserDetails getGuestbookUserDetails() {
		return guestbookUserDetails;
	}

	public void setGuestbookUserDetails(GuestbookUserDetails guestbookUserDetails) {
		this.guestbookUserDetails = guestbookUserDetails;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Date getUserCreate() {
		return userCreate;
	}

	public void setUserCreate(Timestamp userCreate) {
		this.userCreate = userCreate;
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", userName=" + userName + ",  userType=" + userType + ", userCreate=" + userCreate + "]";
	}

}
