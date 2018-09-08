package tn.isi.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.isi.dao.QuestionRepository;
import tn.isi.entites.Question;

@RestController
@CrossOrigin("*")
@RequestMapping("api")
public class QuestionController {

	private final QuestionRepository questionRepository;
	
	@Autowired
	public QuestionController(QuestionRepository questionRepository) {
			this.questionRepository=questionRepository;
	}
	
	
	@RequestMapping(value = "/questionLista", method = RequestMethod.GET)
	public List<Question> getQuestion() {
		return questionRepository.findAll();
	}
	
	public List<Question> getQuestionBySND(@RequestParam(name="mc",defaultValue="") String mc){
		return questionRepository.chercherQuestionBySondage(mc);
	}
}
