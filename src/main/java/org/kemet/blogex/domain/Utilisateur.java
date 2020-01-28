package org.kemet.blogex.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "utilisateur")
@JsonIgnoreProperties({"blogs", "comments"})
public class Utilisateur {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "accid")
	private Long id;

	@Column(name = "accnom")
	private String nom;

	@Column(name = "accprenom")
	private String prenom;
	
	@Email
	@Column(name = "accemail")
	private String mail;
	
	@Column(name = "accphone")
	private String phone;
	
	@Column(name = "acckey")
	private String password;
	
	@Column(name = "accdate")
	@Temporal(TemporalType.DATE)
	private Date signupDate;
	
	@ManyToOne
	@JoinColumn(name = "groupid")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonBackReference
	private Groupe groupe;
	
	@OneToMany(mappedBy = "postedby", cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<Blog> blogs = new HashSet<Blog>();
	
	@OneToMany(mappedBy = "commentby", cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<Commentaire> comments = new HashSet<Commentaire>();


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getSignupDate() {
		return signupDate;
	}

	public void setSignupDate(Date signupDate) {
		this.signupDate = signupDate;
	}

	public Groupe getGroupe() {
		return groupe;
	}

	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}

	public Set<Blog> getBlogs() {
		return blogs;
	}

	public void setBlogs(Set<Blog> blogs) {
		this.blogs = blogs;
	}

	public Set<Commentaire> getComments() {
		return comments;
	}

	public void setComments(Set<Commentaire> comments) {
		this.comments = comments;
	}
	
}
