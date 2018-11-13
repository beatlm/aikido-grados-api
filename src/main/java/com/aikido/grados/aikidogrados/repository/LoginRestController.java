
package com.aikido.grados.aikidogrados.repository;

import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aikido.grados.aikidogrados.model.AuthenticateUser;
import com.aikido.grados.aikidogrados.model.LoginUser;
import com.mongodb.MongoClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = { "http://localhost:4200", "https://aikido-grados.herokuapp.com" }, methods = {
		RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PATCH })
public class LoginRestController {
	@Autowired
	private LoginRepository loginRepository;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<LoginUser> authenticate(@RequestBody AuthenticateUser user) {

		if (StringUtils.equals(user.getUsername(), "bea") && StringUtils.equals(user.getPassword(), "1234")) {
			LoginUser login = new LoginUser();
			login.setName("Usuario de pruebas");
			login.setToken("Token de pruebas");
			return new ResponseEntity<>(login, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<AuthenticateUser> register(@RequestBody AuthenticateUser user) {
		AuthenticateUser newUser = new AuthenticateUser();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(user.getPassword());//TODO encriptar 
		
		loginRepository.save(newUser);
		
		
	//	MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new MongoClient(), "aikido"));

		

		// Insert is used to initially store the object into the database.
	//	mongoOps.insert(newUser);
		//log.info("Insert: " + newUser);

		// Find
		//AuthenticateUser p = mongoOps.findById(newUser.getId(), AuthenticateUser.class);
		AuthenticateUser p = loginRepository.findByUsername(newUser.getUsername());
		log.info("Found: " + p);
		return new ResponseEntity<>(p, HttpStatus.OK);

	}

}
