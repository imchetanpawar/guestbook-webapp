package com.gb.gbd.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gb.gbd.error.LabelErrors;


@Component
@JsonInclude(JsonInclude.Include.NON_EMPTY) 
public class Response {

	public Object object;
	public String status;
	public String message;
	public List<String> labels;
	
	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}

	@Autowired
	public LabelErrors labelErr;
	
	

	public LabelErrors getLabelErr() {
		return labelErr;
	}

	public void setLabelErr(LabelErrors labelErr) {
		this.labelErr = labelErr;
	}


	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
