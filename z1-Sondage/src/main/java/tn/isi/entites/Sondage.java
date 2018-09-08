package tn.isi.entites;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

//win 7atitha ? 
@Entity

public class Sondage implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titre;
	private String descriptions;
	private Date dateCreation;
	private boolean accepted;

	@ManyToOne
	@JoinColumn(name = "CIN_OWNER")
	private User owner;

	@OneToMany(mappedBy = "sondage", fetch = FetchType.EAGER)
	private Collection<Question> questions;

	@ManyToMany(mappedBy = "les_snd_jawibt_alihom")
	private Collection<User> users_jawbou_3aliya;

	@ManyToOne
	private Categorie categorie;

	@Transient
	private MultipartFile sndImage;
	
	/*********** sans parametre *********************/
	public Sondage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/******************* get +set ******************/
	
	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
	@JsonIgnore
	public User getOwner() {
		return owner;
	}
	
	@JsonSetter
	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Collection<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Collection<Question> questions) {
		this.questions = questions;
	}

	public Collection<User> getUsers_jawbou_3aliya() {
		return users_jawbou_3aliya;
	}

	public void setUsers_jawbou_3aliya(Collection<User> users_jawbou_3aliya) {
		this.users_jawbou_3aliya = users_jawbou_3aliya;
	}

	public MultipartFile getSndImage() {
		return sndImage;
	}

	public void setSndImage(MultipartFile sndImage) {
		this.sndImage = sndImage;
	}

	/************** AVEC PARAMETREE *********************/
 	
	public Sondage(String titre, Collection<Question> questions) {
		super();
		this.titre = titre;
		this.questions = questions;
	}

	public Sondage(String titre, Date dateCreation, User owner, Collection<Question> questions) {
		super();
		this.titre = titre;
		this.dateCreation = dateCreation;
		this.owner = owner;
		this.questions = questions;
	}

	public Sondage(String titre, String descriptions, Date dateCreation, boolean accepted, User owner,
			Collection<Question> questions, Collection<User> users_jawbou_3aliya) {
		super();
		this.titre = titre;
		this.descriptions = descriptions;
		this.dateCreation = dateCreation;
		this.accepted = accepted;
		this.owner = owner;
		this.questions = questions;
		this.users_jawbou_3aliya = users_jawbou_3aliya;
	}

	
	public Sondage(String titre, String descriptions, Date dateCreation, User owner, Collection<Question> questions,
			Collection<User> users_jawbou_3aliya) {
		super();
		this.titre = titre;
		this.descriptions = descriptions;
		this.dateCreation = dateCreation;
		this.owner = owner;
		this.questions = questions;
		this.users_jawbou_3aliya = users_jawbou_3aliya;
	}

	public Sondage(String titre, String descriptions, Date dateCreation, User owner) {
		super();
		this.titre = titre;
		this.descriptions = descriptions;
		this.dateCreation = dateCreation;
		this.owner = owner;
	}

	public Sondage(String titre, String descriptions, Date dateCreation, Collection<User> users_jawbou_3aliya) {
		super();
		this.titre = titre;
		this.descriptions = descriptions;
		this.dateCreation = dateCreation;
		this.users_jawbou_3aliya = users_jawbou_3aliya;
	}

	public Sondage(String titre, String descriptions, Date dateCreation) {
		super();
		this.titre = titre;
		this.descriptions = descriptions;
		this.dateCreation = dateCreation;
	}

	public Sondage(String titre, String descriptions, Date dateCreation, User owner,
			Collection<User> users_jawbou_3aliya) {
		super();
		this.titre = titre;
		this.descriptions = descriptions;
		this.dateCreation = dateCreation;
		this.owner = owner;
		this.users_jawbou_3aliya = users_jawbou_3aliya;
	}

	public Sondage(String titre, String descriptions, Date dateCreation, boolean accepted, User owner,
			Collection<Question> questions, Collection<User> users_jawbou_3aliya, Categorie categorie) {
		super();
		this.titre = titre;
		this.descriptions = descriptions;
		this.dateCreation = dateCreation;
		this.accepted = accepted;
		this.owner = owner;
		this.questions = questions;
		this.users_jawbou_3aliya = users_jawbou_3aliya;
		this.categorie = categorie;
	}

	


	

}
