package org.kemet.blogex.entity;


import java.util.Date;
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
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "blog")
@JsonIgnoreProperties("comments")
public class Blog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "blogid")
	private Integer id;
	
	@Column(name = "titre", nullable = false)
	@NotBlank
	private String title;
	
	@Column(name = "contenu", nullable = false)
	@NotBlank
	private String content;
	
	@Column(name = "datepost")
	@Temporal(TemporalType.DATE)
	private Date postOn;
	
	@ManyToOne
	@JoinColumn(name = "accid", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonBackReference
	private Utilisateur postedby;
	
	@OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<Commentaire> comments;

	public Blog() {
		
	}
	
	public Blog(Integer id) {
		this.id = id;
	}
	
	

	/**
	 * @param id
	 * @param title
	 * @param content
	 * @param postOn
	 * @param postedby
	 */
	public Blog(Integer id, @NotBlank String title, @NotBlank String content, Date postOn, Utilisateur postedby) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.postOn = postOn;
		this.postedby = postedby;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPostOn() {
		return postOn;
	}

	public void setPostOn(Date postOn) {
		this.postOn = postOn;
	}

	public Utilisateur getPostedby() {
		return postedby;
	}

	public void setPostedby(Utilisateur postedby) {
		this.postedby = postedby;
	}

	public Set<Commentaire> getComments() {
		return comments;
	}

	public void setComments(Set<Commentaire> comments) {
		this.comments = comments;
	}
	
}
