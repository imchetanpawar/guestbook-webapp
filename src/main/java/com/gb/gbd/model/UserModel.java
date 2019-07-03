package com.gb.gbd.model;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.executable.ValidateOnExecution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gb.gbd.util.Constants;


@Component
@ValidateOnExecution
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@PropertySource("classpath:message.properties")
public class UserModel {

	@NotNull(message= Constants.USERNAME)
	@Size(min=6, max=60, message= Constants.USERNAME)
	private String userName;

	@NotNull(message= Constants.PASSWORD)
	@Size(min=8,max=40)
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,}$", message= Constants.PASSWORD)
	private String password;

	private String userType;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	
}
