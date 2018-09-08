package tn.isi.service;


import tn.isi.entites.AppRole;
import tn.isi.entites.User;

public interface AccountService {
	
	public User saveuser(User user);
	public AppRole saveRole(AppRole role);
	public void addRoletouser(String email,String rolename);
	public User finduserByEmail(String email);

}
