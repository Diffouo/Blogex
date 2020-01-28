package org.kemet.blogex.domain;

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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(name = "commentaire")
public class Commentaire {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "commentid")
	private Long id;
	
	@Column(name = "commenttext")
	private String text;
	
	@Column(name = "commentdate")
	@Temporal(TemporalType.DATE)
	private Date dateCmt;
	
	@ManyToOne
	@JoinColumn(name = "blogid")
	@OnDelete(action = OnDeleteAction.CASCADE)
	//@JsonBackReference(value = "blog")
	private Blog blog;
	
	@ManyToOne
	@JoinColumn(name = "accid")
	@OnDelete(action = OnDeleteAction.CASCADE)
	//@JsonBackReference(value = "commentby")
	private Utilisateur commentby;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
