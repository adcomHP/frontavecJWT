package tn.isi.entites;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id ;
	
	@Column(unique=true)
	private String cin;
	
	@Column(name = "NOM", length = 30)
	private String nom;
	private String prenom;
	private String email;
	private Long tel;
	private String password;
	private Date birth_date;
	private String imageuser;
	private String gender;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@Fetch(value=FetchMode.SUBSELECT)
	private Collection<AppRole> roles = new ArrayList<>() ;
	
	@OneToMany(mappedBy = "owner")
	private Collection<Sondage> les_snd_ALI_sna3thom;

	@ManyToMany
	@JoinTable(name = "S_U_R")
	private Collection<Sondage> les_snd_jawibt_alihom;
			
	@ManyToMany(mappedBy = "users_Answers_Reponces")
	private Collection<Optiona> MyResponces;
	
	@Transient
	private MultipartFile userImage;
	/****************C-SANS-PARAMETRE***************************/
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**************Get-and-set*********************/
	
	
	public String getGender() {
		return gender;
	}

	@JsonIgnore
	public Collection<Optiona> getMyResponces() {
		return MyResponces;
	}

	@JsonSetter
	public void setMyResponces(Collection<Optiona> myResponces) {
		MyResponces = myResponces;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getimageuser() {
		return imageuser;
	}

	public void setimageuser(String imageuser) {
		this.imageuser = imageuser;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getTel() {
		return tel;
	}

	public void setTel(Long tel) {
		this.tel = tel;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@JsonSetter
	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<AppRole> getRoles() {
		return roles;
	}

	public void setRoles(Collection<AppRole> roles) {
		this.roles = roles;
	}

	public Date getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}

	public Collection<Sondage> getLes_snd_ALI_sna3thom() {
		return les_snd_ALI_sna3thom;
	}

	public void setLes_snd_ALI_sna3thom(Collection<Sondage> les_snd_ALI_sna3thom) {
		this.les_snd_ALI_sna3thom = les_snd_ALI_sna3thom;
	}

	@JsonIgnore
	public Collection<Sondage> getLes_snd_jawibt_alihom() {
		return les_snd_jawibt_alihom;
	}
    @JsonSetter
	public void setLes_snd_jawibt_alihom(Collection<Sondage> les_snd_jawibt_alihom) {
		this.les_snd_jawibt_alihom = les_snd_jawibt_alihom;
	}

		
	public MultipartFile getUserImage() {
		return userImage;
	}


	public void setUserImage(MultipartFile userImage) {
		this.userImage = userImage;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/*******************************C-avec-parametre*********************************************/
	
	
		
	public User(String cin, String nom, String prenom, String email, Long tel, String password, Date birth_date) {
		super();
		this.cin = cin;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.tel = tel;
		this.password = password;
		this.birth_date = birth_date;
	}

	public User(String cin, String nom, String prenom, String email, Long tel, String password, Date birth_date,
			Collection<Sondage> les_snd_jawibt_alihom) {
		super();
		this.cin = cin;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.tel = tel;
		this.password = password;
		this.birth_date = birth_date;
		this.les_snd_jawibt_alihom = les_snd_jawibt_alihom;
	}

	public User(String cin, String nom, String prenom, String email, Long tel, String password, Date birth_date,
			Collection<Sondage> les_snd_ALI_sna3thom, Collection<Sondage> les_snd_jawibt_alihom) {
		super();
		this.cin = cin;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.tel = tel;
		this.password = password;
		this.birth_date = birth_date;
		this.les_snd_ALI_sna3thom = les_snd_ALI_sna3thom;
		this.les_snd_jawibt_alihom = les_snd_jawibt_alihom;
	}

	public User(String cin, String nom, String prenom, String email, Long tel, String password, Date birth_date,
			String imageuser) {
		super();
		this.cin = cin;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.tel = tel;
		this.password = password;
		this.birth_date = birth_date;
		this.imageuser = imageuser;
	}

	public User(String cin, String nom, String prenom, String email, Long tel, String password, Date birth_date,
			String imageuser, String gender, Collection<Sondage> les_snd_ALI_sna3thom,
			Collection<Sondage> les_snd_jawibt_alihom, Set<AppRole> roles, Collection<Optiona> myResponces) {
		super();
		this.cin = cin;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.tel = tel;
		this.password = password;
		this.birth_date = birth_date;
		this.imageuser = imageuser;
		this.gender = gender;
		this.les_snd_ALI_sna3thom = les_snd_ALI_sna3thom;
		this.les_snd_jawibt_alihom = les_snd_jawibt_alihom;
		this.roles = roles;
		MyResponces = myResponces;
	}

	public User(String cin, String nom, String prenom, String email, String password, Set<AppRole> roles) {
		super();
		this.cin = cin;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	public User(String cin, String nom, String prenom, String email, Long tel, String password, Date birth_date,
			String imageuser, String gender, Collection<Sondage> les_snd_ALI_sna3thom,
			Collection<Sondage> les_snd_jawibt_alihom, Collection<AppRole> roles, Collection<Optiona> myResponces,
			MultipartFile userImage) {
		super();
		this.cin = cin;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.tel = tel;
		this.password = password;
		this.birth_date = birth_date;
		this.imageuser = imageuser;
		this.gender = gender;
		this.les_snd_ALI_sna3thom = les_snd_ALI_sna3thom;
		this.les_snd_jawibt_alihom = les_snd_jawibt_alihom;
		this.roles = roles;
		MyResponces = myResponces;
		this.userImage = userImage;
	}

	public User(String cin, String nom, String prenom, String email, String password) {
		super();
		this.cin = cin;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
	}

	

	 


	

	
}