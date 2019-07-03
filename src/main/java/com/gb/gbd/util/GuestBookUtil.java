package com.gb.gbd.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import io.jsonwebtoken.impl.crypto.MacProvider;
import java.util.Base64;

@Component
public class GuestBookUtil {
	
	
	private static final Key secret = MacProvider.generateKey(SignatureAlgorithm.HS256);
    private static final byte[] secretBytes = secret.getEncoded();
    private static final String base64SecretBytes = Base64.getEncoder().encodeToString(secretBytes);
	
	@Autowired
    private Environment env;
	
	/**
	 * This method returns the value of the property.
	 * @param key 
	*/
	public String getPropery(String key) {
		return env.getProperty(key);
	}
	
	
	/**
	 * This method is used to encrypt the password
	 * @param password
	 * @return encrypted password.
	*/
	public String encrypt(String password) {
		String encrypted = Jwts.builder()
	        	.setId(password)
	            .signWith(SignatureAlgorithm.HS256, base64SecretBytes)
	            .compact();
		return encrypted;
	}
	
	
	/**
	 * This method is used to decrypt the password
	 * @param encrypted password.
	 * @return original password.
	*/
	public String decrypt(String encPassword) throws Exception {
		try {
		return Jwts.parser()
	            .setSigningKey(base64SecretBytes)
	            .parseClaimsJws(encPassword)
	            .getBody().get("jti").toString();
		}
		catch (Exception e) {
		}
	       return null;     
	}
	
}
