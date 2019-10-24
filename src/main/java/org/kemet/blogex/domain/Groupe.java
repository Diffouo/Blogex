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
	private Integer groupid;	
	
	@Column(name = "grouplabel", nullable = false)

	private String grouplabel;
	
	@OneToMany(mappedBy = "groupe", cascade = CascadeType.ALL)
	@JsonManagedReference
    private Set<Utilisateur> users = new HashSet<>();

	public Integer getGroupid() {
		return groupid;
	}

	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}

	public String getGrouplabel() {
		return grouplabel;
	}

	public void setGrouplabel(String grouplabel) {
		this.grouplabel = grouplabel;
	}

	public Set<Utilisateur> getUsers() {
		return users;
	}

	public void setUsers(Set<Utilisateur> users) {
		this.users = users;
	}
	
}
