package tn.isi.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.isi.entites.Question;
import tn.isi.entites.Sondage;
import tn.isi.entites.User;

public interface SondageRepository extends JpaRepository<Sondage, Long> {

	@Query("select s from Sondage s where s.id = :x")
	public Sondage chercherSndByid(@Param("x") Long id);
	
	public Page<Sondage> findByTitre(String t, Pageable pageable);

	@Query("select s from Sondage s where s.titre like :x")
	public Page<Sondage> chercherSondagePage(@Param("x") String motCle, Pageable pageable);

	@Query("select s from Sondage s where s.dateCreation >:x and s.dateCreation <:y")
	public List<Sondage> chercherSondageDate(@Param("x") Date d1, @Param("y") Date d2);

	@Query("select sndg from  Sondage  sndg  where sndg.owner= :cin")
	public List<Sondage> chercheSondageByUserCreer(@Param("cin") String cin);

	@Query("select sndg from  Sondage sndg join sndg.users_jawbou_3aliya u where u.cin = :cin ")
	public List<Sondage> chercheSondageByUserRependue(@Param("cin") String cin);

	@Query("select sndg  from Sondage sndg join sndg.categorie ctgr where ctgr.name= :title ")
	public List<Sondage> chercheSondageByCategorie(@Param("title") String title);

	@Query("select distinct  sndg  from Sondage sndg  ")
	public List<Sondage> GetNoDuplicate();
	
	public Sondage findOneByTitre(String titre);

	public Sondage findByTitre(String titre);
	
	public Sondage findOneById(Long id);

}
