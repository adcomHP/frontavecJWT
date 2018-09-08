package tn.isi.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import tn.isi.dao.UserRepository;
import tn.isi.entites.User;
import tn.isi.service.AccountService;

@RestController
@CrossOrigin("*")
@RequestMapping("api/user")
public class RegisterController {

	private final UserRepository userRepository;
	private final AccountService accountService;
	
	
	@Autowired
	public RegisterController(UserRepository userRepository,
							  AccountService accountService) {
		this.userRepository = userRepository;
		this.accountService = accountService;
	}

	/********************* ADD-NEW-USER ****************************/
	@RequestMapping(value = "/add", 
			method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> register(@RequestBody User user) {
		if (userRepository.findOneByCin(user.getCin()) != null) {
			return new ResponseEntity<>(new User(), HttpStatus.OK);
		}

		//User createdUser = userRepository.save(user);
		User createdUser = accountService.saveuser(user);
		accountService.addRoletouser(user.getEmail(), "USER");
		
		return new ResponseEntity<>(createdUser, HttpStatus.OK);
	}

	/********************* ADD-USER-IMAGE ****************************/

	@RequestMapping(value = "/add/image", method = RequestMethod.POST)
	public ResponseEntity upload(@RequestParam("id") Long id, HttpServletResponse response,
			HttpServletRequest request) {
		try {
			User user = userRepository.findOneById(id);
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Iterator<String> it = multipartRequest.getFileNames();
			MultipartFile multipartFile = multipartRequest.getFile(it.next());
			String fileName = id + ".png";

			byte[] bytes = multipartFile.getBytes();
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(new File("src/main/resources/static/image/user/" + fileName)));
			stream.write(bytes);
			stream.close();

			return new ResponseEntity("Upload Success!", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity("Upload failed!", HttpStatus.BAD_REQUEST);
		}
	}

	/*********************** GET-LIST-USER *************************/
	@RequestMapping(value = "/usersLista", method = RequestMethod.GET)
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	/********************** GET-USER-BY-ID---POUR L'IMAGE ******************/
	@RequestMapping("/{id}")
	public User getuser(@PathVariable("id") Long id) {
		User user = userRepository.findOneById(id);
		return user;
	}

	/******************* LIST-USER-BY-NOM ********************************/
	@RequestMapping(value = "/usersBy", method = RequestMethod.GET)
	public List<User> getListUserByNom(@RequestParam(name = "mc", defaultValue = "") String mc) {
		return userRepository.chercherUserByNom("%" + mc + "%");
	}

	/*
	 * @RequestMapping(value = "/add", method = RequestMethod.POST) public User
	 * addUserPost(@RequestBody User user) { return userRepository.save(user); }
	 */
}
