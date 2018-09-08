package tn.isi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.isi.entites.Categorie;
import tn.isi.entites.User;

public interface CategorieRpository extends JpaRepository<Categorie, Long> {
	public List<Categorie> findByName(String name);

	@Query("select souCat from  Categorie souCat  join souCat.categoriee e   where e.id = :cat_id ")
	public List<Categorie> findByCategoriees(@Param("cat_id") Long cat_id);

}
