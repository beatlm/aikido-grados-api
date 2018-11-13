package com.aikido.grados.aikidogrados.repository;

import org.springframework.data.repository.CrudRepository;

import com.aikido.grados.aikidogrados.model.AuthenticateUser;

public interface LoginRepository extends CrudRepository<AuthenticateUser, String> {

	public AuthenticateUser findByUsername(String username);
	public AuthenticateUser findByUsernameAndPassword(String username, String password);
}
