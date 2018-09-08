package tn.isi.entites;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;

@Entity

public class Categorie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;

	@ManyToOne
	@JoinColumn(name="Cat_ID")
	private Categorie categoriee;

	@OneToMany(mappedBy = "categoriee", fetch = FetchType.LAZY)
	private Collection<Categorie> sous_Categorie;
	

	@OneToMany(mappedBy = "categorie")
	private Collection<Sondage> sondages;

	/*************SANS PARAMETRE********************/
	public Categorie() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/************ GET + SET **********/
	public Collection<Categorie> getSous_Categorie() {
		return sous_Categorie;
	}

	public void setSous_Categorie(Collection<Categorie> sous_Categorie) {
		this.sous_Categorie = sous_Categorie;
	}

	public Categorie getCategorie() {
		return categoriee;
	}

	public void setCategorie(Categorie categorie) {
		this.categoriee = categorie;
	}

	public Collection<Sondage> getSondages() {
		return sondages;
	}

	public void setSondages(Collection<Sondage> sondages) {
		this.sondages = sondages;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Categorie getCategoriee() {
		return categoriee;
	}

	public void setCategoriee(Categorie categoriee) {
		this.categoriee = categoriee;
	}


	/***********Constrecteur****************/
	
	public Categorie(String name, Categorie categorie, Collection<Categorie> sous_Categorie,
			Collection<Sondage> sondages) {
		super();
		this.name = name;
		this.categoriee = categorie;
		this.sous_Categorie = sous_Categorie;
		this.sondages = sondages;
	}

	
	public Categorie(String name, Categorie categorie) {
		super();
		this.name = name;
		this.categoriee = categorie;
	}

	public Categorie(String name, Collection<Categorie> sous_Categorie) {
		super();
		this.name = name;
		this.sous_Categorie = sous_Categorie;
	}

	public Categorie(String name) {
		super();
		this.name = name;
	}

	public Categorie(String name, Collection<Categorie> sous_Categorie, Collection<Sondage> sondages) {
		super();
		this.name = name;
		this.sous_Categorie = sous_Categorie;
		this.sondages = sondages;
	}

	

	
	
}
