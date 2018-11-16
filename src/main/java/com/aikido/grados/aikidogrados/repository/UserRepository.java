
package com.aikido.grados.aikidogrados.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.aikido.grados.aikidogrados.entity.User;


public interface UserRepository extends CrudRepository<User,String> {
	
	public List<User> findByLicenceLike(String licence);

	public List<User> findByNameLike(String name);

	public List<User> findAll();

}
