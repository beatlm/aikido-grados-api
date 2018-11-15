
package com.aikido.grados.aikidogrados.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aikido.grados.aikidogrados.entity.User;



@RepositoryRestResource(collectionResourceRel = "result", path = "user") 
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080","https://aikido-grados.herokuapp.com"}, 
methods={RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PATCH})
public interface UserRepository extends PagingAndSortingRepository<User,String> {

	@RestResource( rel="buscarPorLicencia", path="findByLicenceNumber" )
	Page<User> findByLicence(@Param("licence") String licence, Pageable pageable); 

	@Query(value="{'name': {$regex: ?0 , $options: 'i' }})")
	List<User> findByQuery(String name);


}
