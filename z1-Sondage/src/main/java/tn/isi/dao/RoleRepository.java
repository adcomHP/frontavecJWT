package tn.isi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.isi.entites.AppRole;

public interface RoleRepository extends JpaRepository<AppRole,Long> {
	public AppRole findByRoleName(String RoleName);

}
