
package com.aikido.grados.aikidogrados.repository;

import org.apache.commons.lang.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aikido.grados.aikidogrados.model.AuthenticateUser;
import com.aikido.grados.aikidogrados.model.TokenData;
import com.aikido.grados.aikidogrados.utils.Encryptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = { "http://localhost:4200", "https://aikido-grados.herokuapp.com" }, methods = {
		RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PATCH })
public class LoginRestController {
	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private Encryptor encryptor;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<TokenData> authenticate(@RequestBody AuthenticateUser user) {

		AuthenticateUser foundUser = loginRepository.findByUsername(user.getUsername());
		log.info("Buscamos por nombre: " + foundUser.getUsername());
		if (foundUser!=null && !StringUtils.isEmpty(foundUser.getId())){
			log.info("Found: " + foundUser.getUsername());
			if(foundUser.getPassword().equals(encryptor.encrypt(user.getPassword()))){
				TokenData tokenData = new TokenData();
				tokenData.setName(foundUser.getUsername());
				tokenData.setToken("Token");
				return new ResponseEntity<>(tokenData, HttpStatus.OK);
			}else {
				log.info("Password recibida; '{}'  password bd: '{}'",foundUser.getPassword(),encryptor.encrypt(user.getPassword()) );
			}
		} 
		log.info("No se ha encontrado al usuario");
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<AuthenticateUser> register(@RequestBody AuthenticateUser user) {
		AuthenticateUser newUser = new AuthenticateUser();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(encryptor.encrypt(user.getPassword()));
		log.info("Guardamos el usuario '{}' con password '{}'", newUser.getUsername(), newUser.getPassword());
		loginRepository.save(newUser);

		AuthenticateUser foundUser = loginRepository.findByUsername(newUser.getUsername());
		log.info("Found: " + foundUser.getUsername());

		return new ResponseEntity<>(foundUser, HttpStatus.OK);

	}

}
