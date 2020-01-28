package org.kemet.blogex.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "groupe")
@JsonIgnoreProperties("users")
public class Groupe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "groupid")
	private Integer id;	
	
	@Column(name = "grouplabel", nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "groupe", cascade = CascadeType.ALL)
	@JsonManagedReference
    private Set<Utilisateur> users = new HashSet<Utilisateur>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Utilisateur> getUsers() {
		return users;
	}

	public void setUsers(Set<Utilisateur> users) {
		this.users = users;
	}	
	
}
