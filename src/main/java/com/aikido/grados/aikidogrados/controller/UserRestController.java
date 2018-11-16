
package com.aikido.grados.aikidogrados.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aikido.grados.aikidogrados.entity.User;
import com.aikido.grados.aikidogrados.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = { "http://localhost:4200","http://localhost:8080", "https://aikido-grados.herokuapp.com" }, methods = {
		RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PATCH })
public class UserRestController {
	@Autowired
	private UserRepository userRepository;


	@RequestMapping(value = "/findByName", method = RequestMethod.GET)
	public ResponseEntity<List<User>> findByName(@RequestParam String name) {
		List<User> foundUser = userRepository.findByNameQuery(name);
		log.info("Se han encontrado {} usuarios ", foundUser.size());
		if(foundUser.isEmpty()) {
			return new ResponseEntity<>( HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(foundUser, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/findByLicence", method = RequestMethod.GET)
	public ResponseEntity<List<User>> findByLicence(@RequestParam String licence) {
		List<User> foundUser = userRepository.findByLicenceQuery(licence);
		log.info("Se han encontrado {} usuarios ", foundUser.size());
		if(foundUser.isEmpty()) {
			return new ResponseEntity<>( HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(foundUser, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public ResponseEntity<List<User>> findAll() {
		List<User> foundUser = userRepository.findAll();
		log.info("Se han encontrado {} usuarios ", foundUser.size());
		if(foundUser.isEmpty()) {
			return new ResponseEntity<>( HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(foundUser, HttpStatus.OK);
		}
	}
}
