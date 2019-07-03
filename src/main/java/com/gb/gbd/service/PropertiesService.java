package com.gb.gbd.service;


import java.io.*;
import java.util.*;

import org.springframework.stereotype.Service;

@Service
public class PropertiesService {
	public static final String PROPERTIES_FILE = "application.properties";
	public static Properties properties = new Properties();
	
	public  Properties readProperties() {
	    InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE);
	    if (inputStream != null) {
	        try {
	            properties.load(inputStream);
	        } catch (IOException e) {
	               e.printStackTrace();
	        }
	    }
	    return properties;
	}
}
