package com.wipro.patient_app.proxy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.wipro.patient_app.entity.ServiceEntity;


@FeignClient(name = "service-application", url = "${service-app-url}:8000")
public interface OpenFeignProxy {

	@GetMapping("/get-all-services")
	public List<ServiceEntity> getAllServicesList();

	@PostMapping("/save-service")
	public ServiceEntity saveService(ServiceEntity serviceEntity);
}
