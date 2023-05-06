package com.wipro.patient_app.entity;

public class EnvEntity {
	private String hostname;
	private String port;
	private String version;
	private String hostaddress;
	
	public EnvEntity(String hostname, String port, String hostaddress, String version) {
		super();
		this.hostname = hostname;
		this.port = port;
		this.hostaddress = hostaddress;
		this.version = version;
	}
	
	public EnvEntity() {
	}
	
	
	
	public String getHostaddress() {
		return hostaddress;
	}

	public void setHostaddress(String hostaddress) {
		this.hostaddress = hostaddress;
	}

	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "EnvEntity [hostname=" + hostname + ", port=" + port + ", version=" + version + ", hostaddress="
				+ hostaddress + "]";
	}


	
}
