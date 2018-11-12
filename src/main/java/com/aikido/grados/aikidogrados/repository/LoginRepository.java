
package com.aikido.grados.aikidogrados.repository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aikido.grados.aikidogrados.model.AuthenticateUser;
import com.aikido.grados.aikidogrados.model.LoginUser;



@RestController
@CrossOrigin(origins = {"http://localhost:4200", "https://aikido-grados.herokuapp.com"}, 
methods={RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PATCH})
public class LoginRepository {

	@RequestMapping(value="/authenticate", method=RequestMethod.POST)
	public ResponseEntity<LoginUser> authenticate(AuthenticateUser user) {
		if(user.getUsername().equals("bea") && user.getPassword().equals("1234")) {
			LoginUser login= new LoginUser();
			login.setName("Usuario de pruebas");
			login.setToken("Token de pruebas");
			return new ResponseEntity<>(login, HttpStatus.OK);
		}else {
			return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
		}
	}


}
