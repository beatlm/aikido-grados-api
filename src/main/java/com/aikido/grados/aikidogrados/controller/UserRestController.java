
package com.aikido.grados.aikidogrados.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aikido.grados.aikidogrados.entity.User;
import com.aikido.grados.aikidogrados.model.AuthenticateUser;
import com.aikido.grados.aikidogrados.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = { "http://localhost:4200", "https://aikido-grados.herokuapp.com" }, methods = {
		RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PATCH })
public class UserRestController {
	@Autowired
	private UserRepository userRepository;


	@RequestMapping(value = "/api/findByName", method = RequestMethod.POST)
	public ResponseEntity<Page<User>> findByName(@RequestBody AuthenticateUser user) {

		Page<User> foundUser = userRepository.findByNameRegex(".*az./i*", null);

		log.info("Se han encontrado {} usuarios ", foundUser.getSize());
		if(foundUser.hasContent()) {
				return new ResponseEntity<>(foundUser, HttpStatus.OK);
		}else {
			return new ResponseEntity<>( HttpStatus.NO_CONTENT);
		}
			

	}

	
}
