package tn.isi.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.isi.entites.Optiona;
import tn.isi.entites.User;

public interface UserRepository extends JpaRepository<User, Long> {

	// public List<User> findByNom(String nom);


	
	@Query("select u from User u where u.nom like :x")
	public Page<User> findByNom(@Param("x") String nom, Pageable pageable);

	@Query("select u from User u where u.nom like :x")
	public List<User> chercherUserByNom(@Param("x") String nom);

	@Query("select u from  User u where u.birth_date > :x and u.birth_date <:y")
	public List<User> chercherUserDateCreation(@Param("x") Date d1, @Param("y") Date d2);

	@Query("select u from  User u  join u.les_snd_jawibt_alihom sndg where sndg.titre =:titre ")
	public Page<User> chercheUserRepondaunsondage(@Param("titre") String titre, Pageable pageable);

	@Query("select u from  User u  join u.les_snd_jawibt_alihom sndg where sndg.titre =:titre ")
	public List<User> chercheUserRepondaunsondage(@Param("titre") String titre);

	@Query("select op from  Optiona  op join  fetch op.qst q  join fetch  q.sondage sng join  fetch  sng.users_jawbou_3aliya usr  where sng.id = :id_SND and usr.cin= :cin ")
	public List<Optiona> GetReponce_OF_User(@Param("cin") String cin, @Param("id_SND") Long id_SND);
	
/*	@Query("select op from  Optiona  op join  op.question q join q.sondage sng join sng.users_jawbou_3aliya usr where sng.id = :id_SND and usr.cin= :cin ")
	public List<Optiona> Get_Liste_Reponce(@Param("cin") String cin, @Param("id_SND") Long id_SND);*/
	
	@Query("select op from  Optiona  op join  op.users_Answers_Reponces ur join op.qst qst join qst.sondage sng   where sng.id = :id_SND and ur.cin= :cin ")
	public List<Optiona> Get_Liste_Reponcesss(@Param("cin") String cin, @Param("id_SND") Long id_SND);
	
	public User findOneByEmail(String email);
	
	public User findOneByEmailAndPassword(String email, String password);
	
	public User findOneByCin(String cin);
	
	public User findOneById(Long id);

	
}
