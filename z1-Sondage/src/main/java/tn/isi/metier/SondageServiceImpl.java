package tn.isi.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.isi.dao.SondageRepository;
import tn.isi.entites.Question;
import tn.isi.entites.Sondage;

@Service
public class SondageServiceImpl implements SondageService {
	
	@Autowired
	private SondageRepository sondageRepository;

	@Override
	public Sondage addSND(Sondage sondage){
		for (Question question: sondage.getQuestions()) {
			question.setSondage(sondage);
		}
		return sondageRepository.save(sondage);
	}
	
}
