package com.aikido.grados.aikidogrados.entity;

import java.util.List;

import org.springframework.data.annotation.Id;

public class User {
	@Id
	private String id;

	private String name;
	private String email;
	private List<UserStatus> status;
	private String grado;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getGrado() {
		return grado;
	}
	public void setGrado(String grado) {
		this.grado = grado;
	}
	public List<UserStatus> getStatus() {
		return status;
	}
	public void setStatus(List<UserStatus> status) {
		this.status = status;
	}

	

}
