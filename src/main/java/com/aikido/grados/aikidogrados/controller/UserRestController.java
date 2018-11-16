
package com.aikido.grados.aikidogrados.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
		log.info("Se han encontrado {} usuarios para la licencia {}", foundUser.size(),licence);
		if(foundUser.isEmpty()) {
			return new ResponseEntity<>( HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(foundUser, HttpStatus.OK);
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> findAll() {
		List<User> foundUser = userRepository.findAll();
		log.info("Se han encontrado {} usuarios ", foundUser.size());
		if(foundUser.isEmpty()) {
			return new ResponseEntity<>( HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(foundUser, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable String id) {
		Optional<User> foundUser = userRepository.findById(id);
		if(foundUser.isPresent()) {
			return new ResponseEntity<>(foundUser.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>( HttpStatus.NO_CONTENT);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
	public ResponseEntity<User> modifyUser(@RequestBody User user) {
		User foundUser = userRepository.save(user);
		if(foundUser!=null) {
			return new ResponseEntity<>( HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>( HttpStatus.NO_CONTENT);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<User> getUser(@RequestBody User user) {
		 User newUser = userRepository.save(user);
		if(newUser!=null) {
			return new ResponseEntity<>(  HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>( HttpStatus.NO_CONTENT);
		}
	}
}
