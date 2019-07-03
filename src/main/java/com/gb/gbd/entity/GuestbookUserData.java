package com.gb.gbd.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;


@Entity
@Table(name = "GUESTBOOK_USER_DATA",schema="TEST1")
@DynamicUpdate
public class GuestbookUserData {

	@Id
	//	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "user_Sequence")
	@SequenceGenerator(name = "user_Sequence", sequenceName = "GUESTBOOK_USER_SEQ")
	@Column(name = "USER_ID")
	private int userID;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "USER_DATA")
	private String userData;

	@Column(name = "APPROVE_FLAG")
	private String approveFlag;
	
	@Column(name = "REMOVE_FLAG")
	private String removeFlag;

	public GuestbookUserData() {
		super();
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

	public String getUserData() {
		return userData;
	}


	public void setUserData(String userData) {
		this.userData = userData;
	}


	public String getApproveFlag() {
		return approveFlag;
	}


	public void setApproveFlag(String approveFlag) {
		this.approveFlag = approveFlag;
	}


	public String getRemoveFlag() {
		return removeFlag;
	}


	public void setRemoveFlag(String removeFlag) {
		this.removeFlag = removeFlag;
	}


	@Override
	public String toString() {
		return "User [userID=" + userID + ", userName=" + userName + ",  userData=" + userData + ", approveFlag=" + approveFlag + ", removeFlag=" + removeFlag + "]";
	}

}
