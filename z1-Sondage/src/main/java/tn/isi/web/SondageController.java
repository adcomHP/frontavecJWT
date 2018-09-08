package tn.isi.web;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.isi.dao.SondageRepository;
import tn.isi.entites.Sondage;

@RestController
@CrossOrigin("*")
@RequestMapping("api/snd")
public class SondageController {
	
	@Autowired
	private SondageRepository sondageRepository ;
	
	@RequestMapping(value="/sndLista/{id}", method = RequestMethod.GET)
	public Sondage findsnd(@PathVariable Long id) {
		return sondageRepository.chercherSndByid(id);
	}
	
	
	@RequestMapping(value = "/sndLista", method = RequestMethod.GET)
	public List<Sondage> getSondages() {
		return sondageRepository.findAll();
	}
	
	
	@RequestMapping(value = "/sndBytitre", method = RequestMethod.GET)
	public Sondage findsondagebytitre(@RequestParam(name = "titre", defaultValue = "") String titre) {
		return sondageRepository.findByTitre(titre);
	}
}
