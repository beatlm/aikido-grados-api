package com.aikido.grados.aikidogrados.repository;

import org.springframework.data.repository.CrudRepository;

import com.aikido.grados.aikidogrados.model.AuthenticateUser;

public interface AuthenticationRepository extends CrudRepository<AuthenticateUser, String> {

	public AuthenticateUser findByUsername(String username);

}
