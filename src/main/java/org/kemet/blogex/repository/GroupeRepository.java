package org.kemet.blogex.repository;

import org.kemet.blogex.entity.Groupe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupeRepository extends JpaRepository<Groupe, Integer> {
	
	public Groupe findByGrouplabel(String grouplabel);

}
