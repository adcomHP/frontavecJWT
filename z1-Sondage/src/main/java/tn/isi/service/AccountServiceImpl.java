package tn.isi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.isi.dao.RoleRepository;
import tn.isi.dao.UserRepository;
import tn.isi.entites.AppRole;
import tn.isi.entites.User;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepositoey;
	
	
	@Override
	public User saveuser(User user) {
		String HashPW = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(HashPW);
		return userRepository.save(user);
	}

	@Override
	public AppRole saveRole(AppRole role) {
		return roleRepositoey.save(role);
	}

	@Override
	public void addRoletouser(String email, String rolename) {
			AppRole role = roleRepositoey.findByRoleName(rolename);
			User user=userRepository.findOneByEmail(email);
			user.getRoles().add(role);
	}

	@Override
	public User finduserByEmail(String email) {
		return userRepository.findOneByEmail(email);
	}

	
	
	
}
