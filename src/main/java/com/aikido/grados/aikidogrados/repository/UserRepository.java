
package com.aikido.grados.aikidogrados.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aikido.grados.aikidogrados.entity.User;


@RequestMapping("/api")
@RepositoryRestResource(collectionResourceRel = "result", path = "user") 
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080","https://aikido-grados.herokuapp.com"}, 
methods={RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PATCH})
public interface UserRepository extends CrudRepository<User,String> {

	/*@RestResource( rel="buscarPorLicencia", path="findByLicenceNumber" )
	public List<User> findByLicence(@Param("licence") String licence);*/ 
	
	@Query(value="{'licence': {$regex: ?0 , $options: 'i' }})")
	public List<User> findByLicenceQuery(String name);

	@Query(value="{'name': {$regex: ?0 , $options: 'i' }})")
	public List<User> findByNameQuery(String name);

	//@RestResource(  path="/api/user/findAll" )
	//public List<User> findAll();

}
