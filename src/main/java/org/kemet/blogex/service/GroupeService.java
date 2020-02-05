package org.kemet.blogex.service;

import java.util.List;
import java.util.stream.Collectors;

import org.kemet.blogex.domain.Groupe;
import org.kemet.blogex.domain.Utilisateur;
import org.kemet.blogex.exception.GroupeNotFoundException;
import org.kemet.blogex.repository.GroupeRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class GroupeService {
	
	private final GroupeRepository groupeRepository;

	public GroupeService(GroupeRepository groupeRepository) {
		this.groupeRepository = groupeRepository;
	}
	
	public Groupe createGroup(Groupe groupe) {
		return groupeRepository.save(groupe);
	}
	
	public Groupe findGroupById(Integer id) {
		return groupeRepository.findById(id).orElseThrow(GroupeNotFoundException::new);
	}
	
	public Groupe findGroupByName(String name) {
		return groupeRepository.findByName(name);
	}
	
	public List<Groupe> findByNameLike(String nameLike){
		return groupeRepository.findByNameContains(nameLike);
	}
	
	public List<Groupe> findAllGroupes() {
		return groupeRepository.findAll();
	}
	
	public List<Groupe> findAllSortedByNameAsc() {
		return groupeRepository.findAll(Sort.by("name").ascending());
	}
	
	public Groupe updateGroupe(Integer id, Groupe newGroup) {
		Groupe group = findGroupById(id);
		group.setName(newGroup.getName());
		return groupeRepository.save(group);
	}
	
	public void deleteGroupe(Integer id) {
		Groupe group = findGroupById(id);
		groupeRepository.delete(group);
	}
	
	public List<Utilisateur> findUsersFromGroup(Integer id) {
		Groupe group = findGroupById(id);
		return group.getUsers().stream().collect(Collectors.toList());
	}

}
