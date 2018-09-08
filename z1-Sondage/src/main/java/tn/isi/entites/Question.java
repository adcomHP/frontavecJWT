package tn.isi.entites;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
public class Question implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String titre_question;
	
	/*fetch = FetchType.LAZY,  cascade = CascadeType.ALL*/
	@OneToMany(mappedBy="qst",cascade = CascadeType.MERGE)
	private Collection<Optiona> options ; 

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ID_SND")
	private Sondage sondage;

	@OneToOne
	@JoinColumn(name="Choosen_Choice")
	private Optiona optiona ; 
	
	/****************C-sans-parametre*******************************/
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/*********get+set********************************************/
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getTitre_question() {
		return titre_question;
	}

	public void setTitre_question(String titre_question) {
		this.titre_question = titre_question;
	}

	@JsonIgnore
	public Sondage getSondage() {
		return sondage;
	}

	@JsonSetter
	public void setSondage(Sondage sondage) {
		this.sondage = sondage;
	}

	public Collection<Optiona> getOptions() {
		return options;
	}

	public void setOptions(Collection<Optiona> options) {
		this.options = options;
	}
	
	public Optiona getOptiona() {
		return optiona;
	}

	public void setOptiona(Optiona optiona) {
		this.optiona = optiona;
	}

/******************C avec parametre**************************************/	

	public Question(String titre_question) {
		super();
		this.titre_question = titre_question;
	}

	public Question(String titre_question, Collection<Optiona> options, Sondage sondage) {
		super();
		this.titre_question = titre_question;
		this.options = options;
		this.sondage = sondage;
	}

			
	public Question(String titre_question, Collection<Optiona> options, Sondage sondage, Optiona optiona) {
		super();
		this.titre_question = titre_question;
		this.options = options;
		this.sondage = sondage;
		this.optiona = optiona;
	}
	
	public Question(String titre_question, Collection<Optiona> options) {
		super();
		this.titre_question = titre_question;
		this.options = options;
	}
	
	
	
	
	
	
}
