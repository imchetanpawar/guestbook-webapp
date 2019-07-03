package com.gb.gbd.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gb.gbd.entity.GuestbookUserData;
import com.gb.gbd.model.Response;
import com.gb.gbd.model.UserARModel;
import com.gb.gbd.model.UserDataModel;
import com.gb.gbd.model.UserModel;
import com.gb.gbd.service.UserValidaterService;
import com.gb.gbd.util.Constants;
import com.gb.gbd.util.GuestBookUtil;


@RestController
@PropertySource("classpath:message.properties")
@RequestMapping("/services")
public class UserFormController {
	
	public final String SUCCESS = Constants.SUCCESS;
	public final String ERROR = Constants.FAIL;
	public final String ADMIN = Constants.ADMIN;

	Response response;

	@Autowired
	UserValidaterService userValidaterService;

	@Autowired
	GuestBookUtil guestBookUtil;

	/**
	 * This method is used to save the user which is login as well as
	 * check if the user is existing or not.
	 * @Logic: if the user exist, it will return response to client so as to proceed with login
	 * else allow new user to get inserted in respective tables. 
	 * @param userModel It consist of all user login information coming from front end.
	 * @return response It returns the response as per inputs from front end.
	*/
	@PostMapping(value = "/saveUser", produces= "application/json", consumes ="application/json")
	@ResponseBody
	public Response saveUser(@Valid @RequestBody UserModel userModel, HttpServletRequest request) throws Exception {
		response = new Response();
		response =  userValidaterService.isUserPresent(userModel);
		if(!response.getMessage().equals(guestBookUtil.getPropery("user.not.exists"))) {
			response.setStatus(ERROR);
			return response;
		}
		else {
			userValidaterService.saveUser(userModel);
			response.setStatus(SUCCESS);
			response.setMessage(guestBookUtil.getPropery("user.created"));
		}
		return response;
	}


	/**	
	 * This method used to store the data entered by logged in user in respective table.
	 * If the user is admin, it returns list of guest users along with its data saved to approve or remove.
		@param userDataModel It consist of data entered by logged in user.
		@return response It will return either success message once data get saved or for admin user list of guest user data.
	*/	
	@PostMapping(value = "/saveUserData", produces= "application/json", consumes ="application/json")
	@ResponseBody
	public Response saveUserData(@Valid @RequestBody UserDataModel userDataModel, HttpServletRequest request) throws Exception {
		response = new Response();

		List<GuestbookUserData> guestbookUserData=null;

		if (userDataModel.getUserType().equalsIgnoreCase(ADMIN)) {
			guestbookUserData = userValidaterService.getUserDataByUserName(userDataModel.getUserName());
			response.setStatus(SUCCESS);
			response.setObject(guestbookUserData);
		}
		else {
			userValidaterService.saveUserData(userDataModel);	
			response.setStatus(SUCCESS);
		}

		return response;
	}

	
	/**
	 * This method is for approve or remove user data, this functionality available for admin user only.
	 * @param userARModel It consist of approve and remove flag changes as processed by Admin.
	 * @return it returns status to success/ error as per data updated in respective table.
	*/
	@PostMapping(value = "/approveRemoveUserData", produces= "application/json", consumes ="application/json")
	@ResponseBody
	public Response approveRemoveUserData(@Valid @RequestBody UserARModel userARModel, HttpServletRequest request) throws Exception {
		response = new Response();
		String isRemoved = userValidaterService.approveRemoveUserData(userARModel);
		response.setStatus(isRemoved);
		return response;
	}

}
