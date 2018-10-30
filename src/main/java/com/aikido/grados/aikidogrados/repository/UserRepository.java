
package com.aikido.grados.aikidogrados.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aikido.grados.aikidogrados.entity.User;



@RepositoryRestResource(collectionResourceRel = "result", path = "user") 

@CrossOrigin(origins = {"http://localhost:4200", "https://aikido-grados-front.herokuapp.com"}, 
methods={RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PATCH})
public interface UserRepository extends PagingAndSortingRepository<User,String> {
	
	@RestResource( rel="buscarPorNombre", path="findByName" )
	List<User> findByName(@Param("name") String name); 

	@RestResource( rel="buscarPorLicencia", path="findByLicenceNumber" )
	List<User> findByLicence(@Param("licence") String licence); 
	
}
