package com.wipro.patient_app.entity;

import org.springframework.beans.factory.annotation.Value;

public class EnvValues {
	
	@Value("${spring.application.name}")
	private String application_name;

	public String getApplication_name() {
		return application_name;
	}

}
