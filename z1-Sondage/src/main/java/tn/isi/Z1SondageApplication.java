package tn.isi;

import java.awt.Robot;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import tn.isi.dao.OptionaRepository;
import tn.isi.dao.QuestionRepository;
import tn.isi.dao.SondageRepository;
import tn.isi.dao.UserRepository;
import tn.isi.entites.AppRole;
import tn.isi.entites.Optiona;
import tn.isi.entites.Question;
import tn.isi.entites.Sondage;
import tn.isi.entites.User;
import tn.isi.service.AccountService;

@SpringBootApplication
public class Z1SondageApplication implements CommandLineRunner{

	@Bean
	public BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}
	
	
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Z1SondageApplication.class, args);

		UserRepository userRepository = ctx.getBean(UserRepository.class);
		SondageRepository sondageRepository = ctx.getBean(SondageRepository.class);
		QuestionRepository questionRepository = ctx.getBean(QuestionRepository.class);
		OptionaRepository optionaRepository = ctx.getBean(OptionaRepository.class);

		/***************************************************
		 * creation des user
		 ************************************************************/
		User bilel = userRepository.save(
				new User("098-bilel", "bilel", "meddeb", "bilel@meddeb.com", (long) 23206993, "123456789", new Date()));
		User hamza = userRepository.save(
				new User("097-hamza", "hamza", "hamzaa", "hamza@hamzaa.com", (long) 23206993, "123456789", new Date()));
		User ADMIN = userRepository.save(
				new User("096-admin", "admin", "adminn", "admin@adminn.com", (long) 23206993, "123456789", new Date()));

		/*******************************
		 * creation 3 sondage bil question w option (sauf snd3 sans queston )
		 *********************************************/

		Optiona o1 = optionaRepository.save(new Optiona("op1-q1-s1"));
		Optiona o2 = optionaRepository.save(new Optiona("op2-q1-s1"));

		Optiona o3 = optionaRepository.save(new Optiona("op1-q2-s1"));
		Optiona o4 = optionaRepository.save(new Optiona("op2-q2-s1"));

		Optiona o5 = optionaRepository.save(new Optiona("op1-q1-s2"));
		Optiona o6 = optionaRepository.save(new Optiona("op2-q1-s2"));
		Optiona o7 = optionaRepository.save(new Optiona("op3-q1-s2"));

		Optiona o8 = optionaRepository.save(new Optiona("op1-q2-s2"));
		Optiona o9 = optionaRepository.save(new Optiona("op2-q2-s2"));

		Optiona yen = optionaRepository.save(new Optiona("yes"));
		Optiona no = optionaRepository.save(new Optiona("no"));
		Optiona NoThanks = optionaRepository.save(new Optiona("NoThanks"));
		
	
		
		Collection<Optiona> les_op_q1_s1 = new ArrayList<Optiona>();
		les_op_q1_s1.add(o1);
		les_op_q1_s1.add(o2);

		Collection<Optiona> les_op_q2_s1 = new ArrayList<Optiona>();
		les_op_q2_s1.add(o3);
		les_op_q2_s1.add(o4);

		Collection<Optiona> les_op_q1_s2 = new ArrayList<Optiona>();
		les_op_q1_s2.add(o5);
		les_op_q1_s2.add(o6);
		les_op_q1_s2.add(o7);

		Collection<Optiona> les_op_q2_s2 = new ArrayList<Optiona>();
		les_op_q2_s2.add(o8);
		les_op_q2_s2.add(o9);
		
		Collection<Optiona> choices_Qestion_MG = new ArrayList<Optiona>();
		choices_Qestion_MG.add(yen);
		choices_Qestion_MG.add(no);
		choices_Qestion_MG.add(NoThanks);
		
		
		Question q1_s1 = questionRepository.save(new Question("q1-s1", les_op_q1_s1));
		Question q2_s1 = questionRepository.save(new Question("q2-s1", les_op_q2_s1));

		Question q1_s2 = questionRepository.save(new Question("q1-s2", les_op_q1_s2));
		Question q2_s2 = questionRepository.save(new Question("q2-s2", les_op_q2_s2));

		Question MG_Questions = questionRepository.save(new Question("Shall we Add more prices", choices_Qestion_MG));

	
		o1.setQst(q1_s1); 
		o2.setQst(q1_s1);
		 
		o3.setQst(q2_s1);
		o4.setQst(q2_s1);

		o5.setQst(q1_s2);
		o6.setQst(q1_s2);
		o7.setQst(q1_s2);

		o8.setQst(q2_s2);
		o9.setQst(q2_s2);
		
		
		yen.setQst(MG_Questions);
		no.setQst(MG_Questions);
		NoThanks.setQst(MG_Questions);

		optionaRepository.save(o1);
		optionaRepository.save(o2);
		optionaRepository.save(o3);
		optionaRepository.save(o4);
		optionaRepository.save(o5);
		optionaRepository.save(o6);
		optionaRepository.save(o7);
		optionaRepository.save(o8);
		optionaRepository.save(o9);

		Collection<Question> les_qus_s1 = new ArrayList<Question>();
		les_qus_s1.add(q1_s1);
		les_qus_s1.add(q2_s1);

		Collection<Question> les_qus_s2 = new ArrayList<Question>();
		les_qus_s2.add(q1_s2);
		les_qus_s2.add(q2_s2);

		Collection<Question> les_qus_MG_Sondage = new ArrayList<Question>();
		les_qus_MG_Sondage.add(MG_Questions);

		Sondage Sondage_s1 = sondageRepository.save(new Sondage("sondages1", new Date(), ADMIN, les_qus_s1));
		Sondage Sondage_s2 = sondageRepository.save(new Sondage("sondagae_s2", les_qus_s2));	
		Sondage sondage_s3 = sondageRepository.save(new Sondage("sondage_s3", "testtest", new Date()));
		Sondage MG_Sondage = sondageRepository.save(new Sondage("sondage_MG", new Date(), ADMIN, les_qus_MG_Sondage));

	   	q1_s1.setSondage(Sondage_s1);
		q2_s1.setSondage(Sondage_s1);

		q1_s2.setSondage(Sondage_s2);
		q2_s2.setSondage(Sondage_s2);
		
		questionRepository.save(q1_s2);
		questionRepository.save(q2_s2);

		questionRepository.save(q1_s1);
		questionRepository.save(q2_s1);

		MG_Questions.setSondage(MG_Sondage);
		questionRepository.save(MG_Questions);
		
		/****************************************/
		Collection<Sondage> Sondage_I_Respond2 = new ArrayList<Sondage>();
		Sondage_I_Respond2.add(MG_Sondage);
		Sondage_I_Respond2.add(Sondage_s1);
		Sondage_I_Respond2.add(Sondage_s2);
		
		hamza.setLes_snd_jawibt_alihom(Sondage_I_Respond2);
		
		Collection<Sondage> Sondage_I_Respond = new ArrayList<Sondage>();
		Sondage_I_Respond.add(MG_Sondage);
		Sondage_I_Respond.add(Sondage_s1);
		
		bilel.setLes_snd_jawibt_alihom(Sondage_I_Respond);

		/*****************************************/
		
		Collection<Optiona> Bilel_Reponse = new ArrayList<Optiona>();
		Bilel_Reponse.add(yen);
		Bilel_Reponse.add(o1);
		Bilel_Reponse.add(o3);
		
		Collection<User> Bilel_R = new ArrayList<User>();
		Bilel_R.add(bilel);
		
		o1.setUsers_Answers_Reponces(Bilel_R);
		yen.setUsers_Answers_Reponces(Bilel_R);
		optionaRepository.save(yen);
		optionaRepository.save(o1);
		
		Collection<User> usermemerep = new ArrayList<User>();
		usermemerep.add(bilel);
		usermemerep.add(hamza);
		
		o3.setUsers_Answers_Reponces(usermemerep);
		optionaRepository.save(o3);
		
		bilel.setMyResponces(Bilel_Reponse);
		userRepository.save(bilel);

		/**************************/
		Collection<Optiona> Hamza_Reponse = new ArrayList<Optiona>();
		Hamza_Reponse.add(NoThanks);
		
		Collection<User> Hamza_R = new ArrayList<User>();
		Hamza_R.add(hamza);
		NoThanks.setUsers_Answers_Reponces(Hamza_R);
		optionaRepository.save(NoThanks);
		hamza.setMyResponces(Hamza_Reponse);
		userRepository.save(hamza);

	

		Collection<User> users = new ArrayList<User>();
		users.add(bilel);
		users.add(hamza);
		
		Sondage_s1.setUsers_jawbou_3aliya(users);
		MG_Sondage.setUsers_jawbou_3aliya(users);
		sondageRepository.save(Sondage_s1);
		sondageRepository.save(MG_Sondage);
		
		/*****************affichage***************/
		List<Optiona> Choices = userRepository.Get_Liste_Reponcesss(hamza.getCin(), Sondage_s1.getId());
		System.out.println("*********************");
		System.out.println("les reponce ali jawib alihom fi sondage s1 :");
		Choices.forEach(e -> System.out.println(e.getReponce()));
		System.out.println("---------------------");
		
		
		/******* arbat il snd b barcha des user **************/
		/*
		 * Collection<Sondage> Sondage_I_Respond = new ArrayList<Sondage>();
		 * Sondage_I_Respond.add(Sondage_s1); Sondage_I_Respond.add(Sondage_s2);
		 * 
		 * bilel.setLes_snd_jawibt_alihom(Sondage_I_Respond);
		 * userRepository.save(bilel);
		 * 
		 * hamza.setLes_snd_jawibt_alihom(Sondage_I_Respond);
		 * userRepository.save(hamza);
		 * 
		 * Collection<User> users =new ArrayList<User>(); users.add(bilel);
		 * users.add(hamza);
		 * 
		 * Sondage_s1.setUsers_jawbou_3aliya(users); sondageRepository.save(Sondage_s1);
		 * Sondage_s2.setUsers_jawbou_3aliya(users); sondageRepository.save(Sondage_s2);
		 */

		/***********
		 * arbat user wahid b reponce wahda just bech ntasti
		 ******************/

		/*
		 * bilel jawib ala deux snd : sondage_s1 et sondage_s2
		 * 
		 */
		/*
		 * Collection<Sondage> Sondage_I_Respond = new ArrayList<Sondage>();
		 * Sondage_I_Respond.add(MG_Sondage); // Sondage_I_Respond.add(Sondage_s2);
		 * 
		 * bilel.setLes_snd_jawibt_alihom(Sondage_I_Respond);
		 * 
		 * Collection<Optiona> choices_Qestion_By_Bilel = new ArrayList<Optiona>();
		 * choices_Qestion_By_Bilel.add(NoThanks);
		 * //bilel.setMyResponces(choices_Qestion_By_Bilel); userRepository.save(bilel);
		 * 
		 * /* hamza jawib ala snd : sondage_s1
		 * 
		 */
		
/*		Collection<Sondage> Sondage_I_Respond2 = new ArrayList<Sondage>();
		Sondage_I_Respond2.add(MG_Sondage);

		hamza.setLes_snd_jawibt_alihom(Sondage_I_Respond2);
		Collection<Optiona> Bilel_Reponse = new ArrayList<Optiona>();
		Bilel_Reponse.add(yen);
		Collection<User> Bilel_R = new ArrayList<User>();
		Bilel_R.add(bilel);
		yen.setUsers_Answers_Reponces(Bilel_R);
		optionaRepository.save(yen);
		bilel.setMyResponces(Bilel_Reponse);
		userRepository.save(bilel);

		Collection<Optiona> Hamza_Reponse = new ArrayList<Optiona>();
		Bilel_Reponse.add(NoThanks);
		Collection<User> Hamza_R = new ArrayList<User>();
		Hamza_R.add(hamza);
		NoThanks.setUsers_Answers_Reponces(Hamza_R);
		optionaRepository.save(NoThanks);
		hamza.setMyResponces(Hamza_Reponse);
		userRepository.save(hamza);

	

		Collection<User> users = new ArrayList<User>();
		users.add(bilel);
		users.add(hamza);
		Sondage_s1.setUsers_jawbou_3aliya(users);
		sondageRepository.save(Sondage_s1);
*/
		/*
		 * a3malit affectation l reponce l qusetion kif namil affichage nilka kima jawib
		 * hamza kima jawib bilel aslan il affectation ala chkoun sarit ala bilel wala
		 * ala hamza khater bilel jawib ala sondage 1 w 2 w hamza jawib kan ala sondage
		 * 1
		 * 
		 * ==> dc il sondage1 jawbou alih bilel w hamza
		 * 
		 * ey chmanaha ?? kifah chnamil ana affectation lil les option ?
		 * 
		 * mnaha les affectation athoukom mta3 les option lichkoun mchaw l hamza wala il
		 * bilel khater taw mchaw li zouz
		 */

		 
		

		/********************
		 * binisba il hamza jawib ala snd akher
		 *******************************/

		/*
		 * Collection<Sondage> Sondage_I_Respond2 = new ArrayList<Sondage>();
		 * Sondage_I_Respond2.add(Sondage_s2);
		 * 
		 * hamza.setLes_snd_jawibt_alihom(Sondage_I_Respond2);
		 * userRepository.save(hamza);
		 * 
		 * Collection<User> users2 =new ArrayList<User>(); users2.add(hamza);
		 * 
		 * Sondage_s2.setUsers_jawbou_3aliya(users2);
		 * sondageRepository.save(Sondage_s2);
		 */

		/*
		 * Collection<Sondage> Sondage_I_Respond2 = new ArrayList<Sondage>();
		 * Sondage_I_Respond2.add(sondage_s3);
		 * 
		 * bilel.setLes_snd_jawibt_alihom(Sondage_I_Respond2);
		 * userRepository.save(bilel);
		 * 
		 * Collection<User> users2 = new ArrayList<User>(); users2.add(bilel);
		 * 
		 * sondage_s3.setUsers_jawbou_3aliya(users2);
		 * sondageRepository.save(sondage_s3);
		 */

		/*
		 * 
		 * SondageRepository sondageRepository = ctx.getBean(SondageRepository.class);
		 * userRepository.save(new User("09814217", "meddeb", "bilel", "m2b@mail.com",
		 * (long) 23206993)); userRepository.save(new User("09814212",
		 * "meddeb2","bilel2", "m2b@mail.com2", (long) 232062993));
		 * 
		 * //userRepository.findAll().forEach(p -> System.out.println(p.getCin()));
		 * 
		 * SondageRepository sondageRepository = ctx.getBean(SondageRepository.class);
		 * sondageRepository.save(new Sondage("zara", "l7wayej", new Date()));
		 * sondageRepository.save(new Sondage("zara2", "l7wayej2", new Date()));
		 * sondageRepository.save(new Sondage("Medicament ", "lgonfoln", new Date()));
		 * sondageRepository.save(new Sondage("Medicament ", "Doliprane ", new Date()));
		 * 
		 * QuestionRepository questionRepository=ctx.getBean(QuestionRepository.class);
		 * questionRepository.save(new Question("chniya rayek"));
		 * questionRepository.save(new Question("chniya rayek2"));
		 * questionRepository.save(new Question("chniya rayek10"));
		 * questionRepository.save(new Question("chniya rayek20"));
		 * 
		 * 
		 * OptionaRepository optionaRepository=ctx.getBean(OptionaRepository.class);
		 * optionaRepository.save(new Optiona("ok1")); optionaRepository.save(new
		 * Optiona("ok2")); optionaRepository.save(new Optiona("ok3"));
		 * optionaRepository.save(new Optiona("ok4")); optionaRepository.save(new
		 * Optiona("ok5")); optionaRepository.save(new Optiona("ok6"));
		 * optionaRepository.save(new Optiona("ok7"));
		 */

		/*
		 * OptionaRepository optionaRepository = ctx.getBean(OptionaRepository.class);
		 * Optiona opa1 = optionaRepository.save(new Optiona("oui")); Optiona opa2 =
		 * optionaRepository.save(new Optiona("Non")); Optiona opa3 =
		 * optionaRepository.save(new Optiona("Aucun")); Optiona opa4 =
		 * optionaRepository.save(new Optiona("YES")); Optiona opa5 =
		 * optionaRepository.save(new Optiona("Nine")); Optiona opa6 =
		 * optionaRepository.save(new Optiona("Naght"));
		 */

		/*
		 *
		 * Optiona o1 = optionaRepository.save(new Optiona("ok1")); Optiona o2 =
		 * optionaRepository.save(new Optiona("ok2")); Optiona o3 =
		 * optionaRepository.save(new Optiona("ok3")); Optiona o4 =
		 * optionaRepository.save(new Optiona("ok4")); Optiona o5 =
		 * optionaRepository.save(new Optiona("ok5")); Optiona o6 =
		 * optionaRepository.save(new Optiona("ok6")); Optiona o7 =
		 * optionaRepository.save(new Optiona("ok7")); Optiona o8 =
		 * optionaRepository.save(new Optiona("ok88"));
		 * 
		 * 
		 * Collection<Optiona> optionas1 = new ArrayList<Optiona>();
		 * 
		 * optionas1.add(o1); optionas1.add(o2);
		 * 
		 * Collection<Optiona> optionas2 = new ArrayList<Optiona>(); optionas2.add(o3);
		 * optionas2.add(o4);
		 * 
		 * Collection<Optiona> optionas3 = new ArrayList<Optiona>(); optionas3.add(o5);
		 * optionas3.add(o6);
		 * 
		 * Collection<Optiona> optionas4 = new ArrayList<Optiona>(); optionas4.add(o7);
		 * optionas4.add(o8);
		 * 
		 * Question q1 = questionRepository.save(new Question("chniya rayek",
		 * optionas1)); Question q2 = questionRepository.save(new
		 * Question("chniya rayek2", optionas2)); Question q3 =
		 * questionRepository.save(new Question("chniya rayek10", optionas3)); Question
		 * q4 = questionRepository.save(new Question("chniya rayek20", optionas4));
		 * 
		 * o1.setQst(q1); o2.setQst(q1); o3.setQst(q2); o4.setQst(q2); o5.setQst(q3);
		 * o6.setQst(q3); o7.setQst(q4); o8.setQst(q4); // modifyin question
		 * 
		 * optionaRepository.save(o1); optionaRepository.save(o2);
		 * optionaRepository.save(o3); optionaRepository.save(o4);
		 * optionaRepository.save(o5); optionaRepository.save(o6);
		 * optionaRepository.save(o7); optionaRepository.save(o8);
		 */

		/*
		 * ...... SondageRepository sondageRepository =
		 * ctx.getBean(SondageRepository.class);
		 * 
		 * UserRepository userRepository = ctx.getBean(UserRepository.class); User bilel
		 * = userRepository.save(new User("4", "bilel", "meddeb", "b@mail.com", (long)
		 * 23206993, "123456789", new Date())); User hamza = userRepository.save(new
		 * User("2", "hamza", "meddeb", "b@mail.com", (long) 23206993, "123456789", new
		 * Date())); User amir = userRepository.save(new User("3", "amir", "meddeb",
		 * "b@mail.com", (long) 23206993, "123456789", new Date())); User mohsen =
		 * userRepository.save(new User("10", "mohsen", "meddeb", "b@mail.com", (long)
		 * 23206993, "123456789", new Date())); User afif = userRepository.save(new
		 * User("6", "afif", "meddeb", "b@mail.com", (long) 23206993, "123456789", new
		 * Date())); User basma = userRepository .save(new User("9", "basma",
		 * "meddeb","b@mail.com", (long) 23206993, "123456789", new Date())); User wahid
		 * =userRepository .save(new User("10", "wahid", "meddeb", "b@mail.com",
		 * (long)23206993, "123456789", new Date())); User adel = userRepository
		 * .save(new User("10", "adel", "meddeb","b@mail.com", (long) 23206993,
		 * "123456789", new Date())); User abdou = userRepository .save(new User("9",
		 * "abdou", "meddeb", "b@mail.com", (long) 23206993, "123456789", new Date()));
		 * 
		 * Sondage snd1 = sondageRepository.save(new Sondage("foot", "foot", new Date(),
		 * bilel)); Sondage snd2 = sondageRepository.save(new Sondage("hnd", "foot", new
		 * Date(), hamza));
		 * 
		 * Collection<Sondage> Sondage_I_Respond = new ArrayList<Sondage>();
		 * Sondage_I_Respond.add(snd2);
		 * 
		 * adel.setLes_snd_jawibt_alihom(Sondage_I_Respond);
		 * bilel.setLes_snd_jawibt_alihom(Sondage_I_Respond);
		 * amir.setLes_snd_jawibt_alihom(Sondage_I_Respond);
		 * abdou.setLes_snd_jawibt_alihom(Sondage_I_Respond);
		 * 
		 * Collection<Sondage> Sondage_I_Respond2 = new ArrayList<Sondage>();
		 * Sondage_I_Respond2.add(snd2);
		 * 
		 * adel.setLes_snd_jawibt_alihom(Sondage_I_Respond2);
		 * basma.setLes_snd_jawibt_alihom(Sondage_I_Respond2);
		 * afif.setLes_snd_jawibt_alihom(Sondage_I_Respond2); //
		 * abdou.setLes_snd_jawibt_alihom(Sondage_I_Respond2);
		 * 
		 * userRepository.save(adel); userRepository.save(wahid);
		 * userRepository.save(basma); userRepository.save(abdou);
		 * 
		 * Collection<User> users1 = new ArrayList<User>(); users1.add(adel);
		 * users1.add(hamza); users1.add(amir); users1.add(abdou);
		 * 
		 * snd1.setUsers_jawbou_3aliya(users1); sondageRepository.save(snd1);
		 * 
		 * Collection<User> users2 = new ArrayList<User>(); users2.add(mohsen);
		 * users2.add(wahid); users2.add(afif); users2.add(abdou);
		 * snd2.setUsers_jawbou_3aliya(users2);
		 * 
		 * sondageRepository.save(snd2);
		 * 
		 * q1.setSondage(snd2); q2.setSondage(snd2); q3.setSondage(snd1);
		 * q4.setSondage(snd1);
		 * 
		 * // Nouveau Update q1.setOptiona(o1); q2.setOptiona(o4);
		 * 
		 * questionRepository.save(q1); questionRepository.save(q2);
		 * questionRepository.save(q3); questionRepository.save(q4); // sous Categories
		 * 
		 * Collection<Categorie> categ_Multimidia_Liste = new ArrayList<Categorie>(); //
		 * Categorie Categorie categ_Multimidia = new
		 * Categorie("Sport",categ_Multimidia_Liste); // sousCategory1 Categorie
		 * telephone = new Categorie("Natation", categ_Multimidia); ..... Categorie Pc =
		 * new Categorie("Foot", categ_Multimidia); // sousCategory2
		 * categ_Multimidia_Liste.add(telephone); categ_Multimidia_Liste.add(Pc);
		 * 
		 * CategorieRpository cateorieRepository =
		 * ctx.getBean(CategorieRpository.class);
		 * cateorieRepository.save(categ_Multimidia); cateorieRepository.save(Pc);
		 * cateorieRepository.save(telephone);
		 * 
		 * Page<User> use = userRepository.chercheUserRepondaunsondage("hnd", new //
		 * PageRequest(0, 3)); use.forEach(e -> System.out.println(e.getNom()));
		 * List<Sondage>ss =
		 * abdou.getLes_snd_jawibt_alihom().forEach(e->{e.getQuestions().forEach(z->z.
		 * getOptiona().getReponce()); return e ; }); List<Optiona> Choices =
		 * userRepository.Get_Liste_Reponce(abdou.getCin(), snd2.getId());
		 * Choices.forEach(e -> System.out.println(e.getReponce()));
		 * 
		 * List<Categorie> soucateg =
		 * cateorieRepository.findByCategoriees(categ_Multimidia.getId());
		 * 
		 * soucateg.forEach(e->{System.out.println(e.getName());}); Page<User> findd =
		 * userRepository.findByNom("bilel", new PageRequest(0, 1)); findd.forEach(e ->
		 * System.out.println(e.getNom()));
		 **/

		/*
		 * SondageRepository sondageRepository =ctx.getBean(SondageRepository.class);
		 * sondageRepository.save(new Sondage("snd1", "zara", "mrawil", new Date(),
		 * true, bilel));
		 */
		// sondageRepository.GetNoDuplicate().forEach(e->System.out.println(e.getCategorie()));

	}


	@Autowired
	private AccountService accountService;
	
	@Override
	public void run(String... args) throws Exception {
		accountService.saveuser(new User("456789", "med", "bbb", "b@bb.com", "123"));
		accountService.saveuser(new User("777777", "mse", "sdb", "b@cb.com", "123"));
		accountService.saveRole(new AppRole("ADMIN"));
		accountService.saveRole(new AppRole("USER"));
		accountService.addRoletouser("b@cb.com", "ADMIN");
		accountService.addRoletouser("b@bb.com", "USER");



	}

}
