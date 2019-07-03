package com.gb.gbd.test;


import static org.junit.Assert.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.gb.gbd.model.UserARModel;
import com.gb.gbd.model.UserDataModel;
import com.gb.gbd.model.UserModel;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class UserFormControllerTest {
	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private UserModel userModel;

	@Autowired
	private UserDataModel userDataModel;

	@Autowired
	private	UserARModel userARModel;

	JSONObject jsonObject;


	@Test 
	public void testUserSave() throws URISyntaxException, JSONException {
		restTemplate =new TestRestTemplate();

		userModel = new UserModel(); 
		userModel.setUserName("userName33");
		userModel.setPassword("!A123abcd"); 
		userModel.setUserType("Guest");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		URI uri = new URI("http://localhost:9000/services/saveUser");
		HttpEntity<UserModel> request = new HttpEntity<>(userModel, headers);

		ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request,String.class); 
		jsonObject = new JSONObject(result.getBody());

		assertEquals("user created successfully.",jsonObject.get("message")); 
	}

	@Test 
	public void testUserSaveAfter() throws URISyntaxException, JSONException {
		restTemplate=new TestRestTemplate();

		userModel = new UserModel(); 
		userModel.setUserName("userName33");
		userModel.setPassword("!A123abcd");
		userModel.setUserType("userType0");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		URI uri = new URI("http://localhost:9000/services/saveUser");
		HttpEntity<UserModel> request = new HttpEntity<>(userModel, headers);

		ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request,String.class); 
		jsonObject = new JSONObject(result.getBody());

		assertEquals("The username you have entered already exists, please enter a new one.",jsonObject.get("message")); 

	}


	@Test 
	public void testUserDataSave() throws URISyntaxException, JSONException
	{

		restTemplate = new TestRestTemplate();

		userDataModel= new UserDataModel();
		userDataModel.setUserName("userName1");
		userDataModel.setUserData("userData1"); 
		userDataModel.setApproveFlag("Y");
		userDataModel.setRemoveFlag("N"); 
		userDataModel.setUserType("guest");


		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		URI uri = new URI("http://localhost:9000/services/saveUserData");
		HttpEntity<UserDataModel> request = new HttpEntity<>(userDataModel, headers);

		ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request,String.class); 
		jsonObject = new JSONObject(result.getBody());

		assertEquals("success", jsonObject.get("status")); }


	@Test public void testApproveRemoveUserData() throws URISyntaxException, JSONException
	{

		restTemplate = new TestRestTemplate();

		userARModel= new UserARModel(); 

		userARModel.setUserName("userName10");
		userARModel.setUserData("userData"); 
		userARModel.setApproveFlag("N");
		userARModel.setRemoveFlag("N");
		userARModel.setUserId(9650);


		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		URI uri = new URI("http://localhost:9000/services/approveRemoveUserData");
		HttpEntity<UserARModel> request = new HttpEntity<>(userARModel, headers);

		ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request,String.class); 
		jsonObject = new JSONObject(result.getBody());

		assertEquals("success", jsonObject.get("status")); 

	}

}
