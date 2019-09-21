package org.kemet.blogex.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(name = "commentaire")
public class Commentaire {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "commentid")
	private Integer id;
	
	@Column(name = "commenttext", nullable = false)
	@NotBlank
	private String text;
	
	@Column(name = "commentdate", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dateCmt;
	
	@ManyToOne
	@JoinColumn(name = "blogid", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	//@JsonBackReference(value = "blog")
	private Blog blog;
	
	@ManyToOne
	@JoinColumn(name = "accid", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	//@JsonBackReference(value = "commentby")
	private Utilisateur commentby;


	public Commentaire() {

	}


	/**
	 * @param id
	 * @param text
	 * @param dateCmt
	 * @param blog
	 * @param commentby
	 */
	public Commentaire(Integer id, @NotBlank String text, Date dateCmt, Blog blog, Utilisateur commentby) {
		this.id = id;
		this.text = text;
		this.dateCmt = dateCmt;
		this.blog = blog;
		this.commentby = commentby;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public Date getDateCmt() {
		return dateCmt;
	}


	public void setDateCmt(Date dateCmt) {
		this.dateCmt = dateCmt;
	}


	public Blog getBlog() {
		return blog;
	}


	public void setBlog(Blog blog) {
		this.blog = blog;
	}


	public Utilisateur getCommentby() {
		return commentby;
	}


	public void setCommentby(Utilisateur commentby) {
		this.commentby = commentby;
	}

}
