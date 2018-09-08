package tn.isi.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import tn.isi.dao.OptionaRepository;
import tn.isi.dao.QuestionRepository;
import tn.isi.dao.SondageRepository;
import tn.isi.entites.Optiona;
import tn.isi.entites.Question;
import tn.isi.entites.Sondage;
import tn.isi.entites.User;


@RestController
@CrossOrigin("*")
@RequestMapping("api/snd")
public class SondageRegisterController {
	
	private final SondageRepository sondageRepository;
	private final QuestionRepository questionRepository ;
	private final OptionaRepository optionaRepository;
	
	@Autowired
	public SondageRegisterController(SondageRepository sondageRepository , OptionaRepository optionaRepository , QuestionRepository questionRepository) {
		this.sondageRepository=sondageRepository;
		this.questionRepository=questionRepository;
		this.optionaRepository=optionaRepository;
	}
	
/*********************************Save-snd*************************************/
 	@RequestMapping(
			value="/savesond",
			method = RequestMethod.POST,
			produces= MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<?>registersondag(@RequestBody Sondage sondage){
			
	
		if(sondageRepository.findOneByTitre(sondage.getTitre())!=null) {
			return new ResponseEntity<>(new Sondage(),HttpStatus.OK);
		}
						
	
		Sondage createdSondage=sondageRepository.save(sondage);
		
	  	for(Question question : sondage.getQuestions())
		{
			question.setSondage(createdSondage);
			Question createdQuestion =questionRepository.save(question);
			
				for (Optiona optiona: question.getOptions())
					{
						optiona.setQst(createdQuestion);
						Optiona CreatedOption= optionaRepository.save(optiona);
					}
		}
		
		return new ResponseEntity<>(createdSondage,HttpStatus.OK);
	
	
	}

 	/********************* ADD-SND-IMAGE ****************************/

	@RequestMapping(value = "/savesond/image", method = RequestMethod.POST)
	public ResponseEntity upload(@RequestParam("id") Long id, HttpServletResponse response,
			HttpServletRequest request) {
		try {
			Sondage sondage = sondageRepository.findOneById(id);
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Iterator<String> it = multipartRequest.getFileNames();
			MultipartFile multipartFile = multipartRequest.getFile(it.next());
			String fileName = id + ".png";

			byte[] bytes = multipartFile.getBytes();
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(new File("src/main/resources/static/image/snd/" + fileName)));
			stream.write(bytes);
			stream.close();

			return new ResponseEntity("Upload Success!", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity("Upload failed!", HttpStatus.BAD_REQUEST);
		}
	}
	
	
	/********************** GET-USER-BY-ID---POUR L'IMAGE ******************/
	@RequestMapping("/{id}")
	public Sondage getSondage(@PathVariable("id") Long id) {
		Sondage sondage = sondageRepository.findOneById(id);
		return sondage;
	}

	/****************Pour le test afichage fil console ***************************/
		
	
/*
	@RequestMapping(
			value="/savesndd",
			method = RequestMethod.POST,
			produces= MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
			)
	public Sondage registersondage(@RequestBody Sondage sondage){
		
	
		JSONObject a = new JSONObject(sondage);

	
		System.out.println("****le json object a :*******");
		System.out.println(a);
		System.out.println("-------------------");
		
		JSONArray qus = a.getJSONArray("questions");
		
		for (int i=0; i< qus.length(); ++i)
		{
			JSONObject qu =qus.getJSONObject(i);
			Gson gson =new GsonBuilder().create();
			String titre_qu = qu.getString("titre_question");
			System.out.println("la liste des titre des question est :^^^^^^^^^^^^^^");
			System.out.println(titre_qu);

		}
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		return sondage;

	}
	
*/
	
}
