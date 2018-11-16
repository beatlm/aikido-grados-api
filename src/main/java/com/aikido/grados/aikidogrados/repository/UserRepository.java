
package com.aikido.grados.aikidogrados.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.aikido.grados.aikidogrados.entity.User;


public interface UserRepository extends CrudRepository<User,String> {
	
	public List<User> findByLicenceLike(String licence);
	@Query(value="{'licence': {$regex: ?0 , $options: 'i' }})")
	public List<User> findByLicenceQuery(String licence);
	@Query(value="{'id': {$regex: ?0 , $options: 'i' }})")
	public List<User> findByIdQuery(String id);

	@Query(value="{'name': {$regex: ?0 , $options: 'i' }})")
	public List<User> findByNameQuery(String name);

	public List<User> findAll();

}
