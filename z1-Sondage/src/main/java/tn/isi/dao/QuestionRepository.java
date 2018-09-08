package tn.isi.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.isi.entites.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

	@Query(" select s from Sondage s where s.id= :x")
	public Page<Question> chercherQuestionBySondagePage(@Param("x")String idSondage, Pageable pageable);


	@Query(" select s from Sondage s where s.id= :x")
	public List<Question> chercherQuestionBySondage(@Param("x")String idSondage);
    
	
	
}
