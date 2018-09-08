package tn.isi.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.isi.entites.Optiona;
import tn.isi.entites.Sondage;

public interface OptionaRepository extends JpaRepository<Optiona, Long>{

	@Query("select q from  Question q where q.id = :x")
	public List<Optiona> chercherOptionaByQuestion(@Param("x") Long idQuestion );

}
