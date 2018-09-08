package tn.isi.web;

import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tn.isi.dao.UserRepository;
import tn.isi.entites.User;

@RestController
@CrossOrigin("*")
@RequestMapping("api/user")
public class LoginController {
	
	
	private final UserRepository userRepository;
	
	@Autowired
	public LoginController(UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	
	
	/********************LOGIN****************************/
	@RequestMapping(
			value="/loginn",
			method = RequestMethod.POST,
			produces= MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
			)
	
	public ResponseEntity<?> login(@RequestBody User user){
		if (StringUtils.isEmpty(user.getEmail())|| StringUtils.isEmpty(user.getPassword())) {
			return new ResponseEntity<>(new User(), HttpStatus.OK);
		}
		if (userRepository.findOneByEmailAndPassword(user.getEmail(), user.getPassword())== null) {
			return new ResponseEntity<>(new User(),HttpStatus.OK);
		}
		return new ResponseEntity<>(userRepository.findOneByEmail(user.getEmail()),HttpStatus.OK);
	}
	
	
	
	
	
	@RequestMapping("/token")
	public Map<String, String> token(HttpSession session, HttpServletRequest request) {
		System.out.println(request.getRemoteHost());
		
		String remoteHost = request.getRemoteHost();
		int portNumber = request.getRemotePort();
		
		System.out.println(remoteHost+":"+portNumber);
		System.out.println(request.getRemoteAddr());
		
		return Collections.singletonMap("token", session.getId());
	}
	
	
	@RequestMapping(value="/checkSession")
	public ResponseEntity checkSession() {
		return new ResponseEntity("Session active",HttpStatus.OK);
	}
	
	

}
