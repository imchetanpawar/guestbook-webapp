package com.gb.gbd.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.gb.gbd.entity.GuestbookUser;
import com.gb.gbd.entity.GuestbookUserData;
import com.gb.gbd.entity.GuestbookUserDetails;
import com.gb.gbd.error.LabelErrors;
import com.gb.gbd.model.Response;
import com.gb.gbd.model.UserARModel;
import com.gb.gbd.model.UserDataModel;
import com.gb.gbd.model.UserModel;
import com.gb.gbd.repository.GuestBookUserDataRepository;
import com.gb.gbd.util.GuestBookUtil;

@Service
@PropertySource("classpath:message.properties")
public class UserValidaterService {

	@Autowired
	private Environment env;

	@Autowired
	GuestBookUserServiceImp guestBookUserServiceImp;

	@Autowired
	UserServiceImp userServiceImp;

	@Autowired
	GuestBookUserDataRepository guestBookUserDataRepository;

	@Autowired
	GuestBookUtil guestBookUtil;

	/**
	 * This method check the user is existing or new one. If user exist, it returns response with user exist message.
	 * @param userModel from which userName extracted to check it is existing or new from table.
	*/
	public Response isUserPresent(UserModel userModel) {
		Response response = new Response();
		String userName = userModel.getUserName();
		LabelErrors labelError = new LabelErrors();

		if (userServiceImp.isUserExists(userName)) {
			labelError.setUserName(env.getProperty("user.exists"));
			response.setMessage(env.getProperty("user.exists"));
			response.setLabelErr(labelError);
			return response;
		}

		response.setMessage(env.getProperty("user.not.exists"));
		return response;

	}

	/**
	 * This method save the new user along with its password in security table in encrypted format.
	*/
	public GuestbookUser saveUser(UserModel userModel) throws Exception {

		GuestbookUser guestbookUser = null;
		GuestbookUserDetails guestbookUserDetails = null;
		String encryptedPassword="";

		Date date = new Date();
		Timestamp userCreatedDate = new Timestamp(date.getTime());

		if (guestbookUser == null) {
			guestbookUser = new GuestbookUser();
			guestbookUserDetails = new GuestbookUserDetails();
		} else {
			guestbookUserDetails = guestbookUser.getGuestbookUserDetails();
		}

		BeanUtils.copyProperties(userModel, guestbookUser);
		guestbookUser.setUserCreate(userCreatedDate);
		guestbookUser.setUserName(userModel.getUserName());
		guestbookUserDetails.setUserName(userModel.getUserName());

		encryptedPassword=guestBookUtil.encrypt(userModel.getPassword());

		guestbookUserDetails.setPassWordSHA(encryptedPassword);

		guestbookUser.setGuestbookUserDetails(guestbookUserDetails);

		guestBookUserServiceImp.saveUser(guestbookUser);

		return guestbookUser;
	}


	/**
	 * This method save the logged in user data.
	 * 
	 * 	*/
	public GuestbookUserData saveUserData(UserDataModel userDataModel) throws Exception {
		GuestbookUserData guestbookUserData = new GuestbookUserData();

		BeanUtils.copyProperties(userDataModel, guestbookUserData);
		guestbookUserData.setUserName(userDataModel.getUserName());
		guestbookUserData.setUserData(userDataModel.getUserData());
		guestbookUserData.setApproveFlag(userDataModel.getApproveFlag());
		guestbookUserData.setRemoveFlag(userDataModel.getRemoveFlag());

		guestBookUserServiceImp.saveUserData(guestbookUserData);
		return guestbookUserData;
	}

	/**
	 * This method is used to retrieve list of guest user data along with its approve / remove flag.
	 * @param userName it will return data on the basis of userName.
	*/
	public List<GuestbookUserData> getUserDataByUserName(String userName) {
		List<GuestbookUserData> guestbookUserData = guestBookUserDataRepository.findByUserName().orElse(null);
		return guestbookUserData;
	}


	/**
	 * This method is used to store data entered by guest user.
	*/
	public void saveUser(GuestbookUserData user) {
		guestBookUserDataRepository.saveAndFlush(user);
	}


	/**
	 * This method is used to update approve/ remove flag. done by Admin user only.
	*/
	public String approveRemoveUserData(UserARModel userARModel) {
		GuestbookUserData guestbookUserData= new GuestbookUserData();
		guestbookUserData.setUserID(userARModel.getUserId());
		guestbookUserData.setUserName(userARModel.getUserName());
		guestbookUserData.setUserData(userARModel.getUserData());
		guestbookUserData.setApproveFlag(userARModel.getApproveFlag());
		guestbookUserData.setRemoveFlag(userARModel.getRemoveFlag());

		guestBookUserDataRepository.saveAndFlush(guestbookUserData);
		return "success";

	}

}