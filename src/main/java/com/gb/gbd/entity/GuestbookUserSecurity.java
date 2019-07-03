package com.gb.gbd.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="GUESTBOOK_USER_SECURITY" ,schema="TEST1")
public class GuestbookUserSecurity {

	@Id
	//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "usercontact_Sequence")
	@SequenceGenerator(name = "usercontact_Sequence", sequenceName = "GUESTBOOK_USERCNT_SEQ")
	@Column(name = "USER_ID")
	private int userId;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "PASSWORD_SHA")
	private String passWordSHA;



	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWordSHA() {
		return passWordSHA;
	}

	public void setPassWordSHA(String passWordSHA) {
		this.passWordSHA = passWordSHA;
	}



}
