package com.wipro.service_app.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.service_app.entity.ServiceEntity;
import com.wipro.service_app.repos.ServicesRepos;

@RestController
public class Controller {

	@Autowired
	ServicesRepos services_repos;
	@Autowired
	Environment env;
	@Value("${spring.application.name}")
	private String application_name;


	@GetMapping("/")
	public String getallServices() throws UnknownHostException {
		String port = env.getProperty("local.server.port");
		String h_name = InetAddress.getLocalHost().getHostName();
		String h_addr = InetAddress.getLocalHost().getHostAddress();
		String version= "1";
		

		return application_name.toUpperCase() + " [hostname=" + h_name + ", port=" + port + ", version=" + version + ", hostaddress="
		+ h_addr + "]" + '\n';
	}

	@GetMapping("/get-all-services")
	public List<ServiceEntity> getAllServices() {
		return services_repos.findAll();
	}

	@PostMapping("/save-service")
	public ServiceEntity saveService(ServiceEntity serviceEntity) {
		System.out.println(serviceEntity.getName());
		ServiceEntity se = services_repos.save(serviceEntity);
		return se;
	}

}
