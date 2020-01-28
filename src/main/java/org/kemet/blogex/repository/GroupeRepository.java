package org.kemet.blogex.repository;

import java.util.List;

import org.kemet.blogex.domain.Groupe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupeRepository extends JpaRepository<Groupe, Integer> {
	
	public Groupe findByName(String name);
	
	public List<Groupe> findByNameContains(String nameLike);

}
