package tn.isi.entites;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
public class Optiona implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String reponce;

	@OneToOne(mappedBy="optiona")
	private Question question;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Question qst;
	
	@ManyToMany
	@JoinTable(name = "Responce_User")

	private Collection<User> users_Answers_Reponces;

	/**************sans para*********************/

	public Optiona() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/*********Get and set*********************/
	
	
	
	public Long getId() {
		return id;
	}

	public Collection<User> getUsers_Answers_Reponces() {
		return users_Answers_Reponces;
	}

	public void setUsers_Answers_Reponces(Collection<User> users_Answers_Reponces) {
		this.users_Answers_Reponces = users_Answers_Reponces;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReponce() {
		return reponce;
	}

	public void setReponce(String reponce) {
		this.reponce = reponce;
	}

	@JsonIgnore
	public Question getQst() {
		return qst;
	}
	@JsonSetter
	public void setQst(Question qst) {
		this.qst = qst;
	}

	@JsonIgnore
	public Question getQuestion() {
		return question;
	}

	@JsonSetter
	public void setQuestion(Question question) {
		this.question = question;
	}

/****************constructeur*****************/	
	public Optiona(String reponce) {
		super();
		this.reponce = reponce;
	}
	public Optiona(String reponce, Question question, Question qst) {
		super();
		this.reponce = reponce;
		this.question = question;
		this.qst = qst;
	}
	
	public Optiona(String reponce, Question qst) {
		super();
		this.reponce = reponce;
		this.qst = qst;
	}


	
}
