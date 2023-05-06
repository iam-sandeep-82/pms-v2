package com.wipro.patient_app.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wipro.patient_app.entity.EnvEntity;
import com.wipro.patient_app.entity.EnvValues;
import com.wipro.patient_app.entity.PatientEntity;
import com.wipro.patient_app.entity.ServiceEntity;
import com.wipro.patient_app.proxy.OpenFeignProxy;
import com.wipro.patient_app.repos.PatientRepos;
import com.wipro.patient_app.service.PatientService;

@org.springframework.stereotype.Controller
public class Controller {

	@Autowired
	PatientRepos patient_repos;
	@Autowired
	PatientService patient_service;
	@Autowired
	OpenFeignProxy feign_proxy;
	@Autowired
	Environment env;

	
	@Value("${spring.application.name}")
	private String application_name;

	@GetMapping("/")
	public String __(ModelMap model) throws UnknownHostException {
		String port = env.getProperty("local.server.port");
		String h_name = InetAddress.getLocalHost().getHostName();
		String h_addr = InetAddress.getLocalHost().getHostAddress();
		String version= "1";
		
		String env_entity = application_name.toUpperCase() + " [hostname=" + h_name + ", port=" + port + ", version=" + version + ", hostaddress="
				+ h_addr + "]" + '\n';

		model.addAttribute("env", env_entity);
		return "index";
	}

	@GetMapping("/home")
	public String getHome(ModelMap model) {
		model.put("message", "nice to see you again");
		return "index";
	}

	@GetMapping("/reg-patient")
	public String regPatient(ModelMap model) {
		List<ServiceEntity> services = feign_proxy.getAllServicesList();
		model.put("list_services_id", services);
		return "regPatient";
	}

	@GetMapping("/save-patient")
	public String savePatient(ModelMap model, @ModelAttribute("firstname") String firstname,
			@ModelAttribute("lastname") String lastname,
			@RequestParam("selected_service_id") List<Integer> selected_service_id) {

		PatientEntity new_patient = new PatientEntity(firstname, lastname, selected_service_id);
		PatientEntity save_patient = patient_service.savePatient(new_patient);
		if (save_patient != null) {
			model.put("patient_name", save_patient.getFirstname());
			model.put("key", "value");
			model.addAttribute("patient_name", "harry");
			return "regSuccess";
		}
		return "regFailed";
	}

	@GetMapping("/show-patient")
	public String showPatient(ModelMap model) {
		List<PatientEntity> list_patients = patient_service.findAll();
		model.put("list_patients", list_patients);
		return "showPatient";
	}

	//	SERVICE

	@GetMapping("/show-service")
	public String showService(ModelMap model) {
		List<ServiceEntity> services = feign_proxy.getAllServicesList();
		model.put("list_services", services);
		return "showServices";
	}

	@GetMapping("/reg-service")
	public String regService(ModelMap model) {
		return "regService";
	}

	@PostMapping("/save-service")
	public String saveService(ModelMap model, @RequestParam("name") String name, @RequestParam("price") int price,
			@RequestParam("ids") int ids) {
		System.out.println("SERVICE NAME " + name);
		System.out.println(price);
		System.out.println(ids);
		ServiceEntity serviceEntity = new ServiceEntity(ids, name, price);
		ServiceEntity save_service = feign_proxy.saveService(serviceEntity);

		if (save_service != null) {
			model.put("patient_name", save_service.getName());
			model.put("key", "value");
			return "regSuccess";
		}
		return "regFailed";
	}

}
