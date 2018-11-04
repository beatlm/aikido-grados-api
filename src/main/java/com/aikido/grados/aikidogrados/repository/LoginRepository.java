
package com.aikido.grados.aikidogrados.repository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aikido.grados.aikidogrados.model.LoginUser;



@RestController
@CrossOrigin(origins = {"http://localhost:4200", "https://aikido-grados-front.herokuapp.com"}, 
methods={RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PATCH})
public class LoginRepository {
	
	@RequestMapping(value="/authenticate", method=RequestMethod.POST)
    public LoginUser authenticate() {
		LoginUser login= new LoginUser();
		login.setName("Usuario de pruebas");
        return login;
    }
	
	
}
