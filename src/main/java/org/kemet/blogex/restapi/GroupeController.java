package org.kemet.blogex.restapi;

import java.util.List;

import javax.validation.Valid;

import org.kemet.blogex.domain.Groupe;
import org.kemet.blogex.domain.Utilisateur;
import org.kemet.blogex.service.GroupeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/groups")
public class GroupeController {
	
	private final GroupeService groupeService;
	
	public GroupeController(GroupeService groupeService) {
		this.groupeService = groupeService;
	}

	//Getting all the groups
	@GetMapping
	public List<Groupe> getAllGroup(){
		return groupeService.findAllGroupes();
	}
	
	//Get all the users of the given groupID
	@GetMapping("/{id}/users")
	public List<Utilisateur> findUsersByGroupe(@PathVariable(name = "id") Integer groupID){
		return groupeService.findUsersFromGroup(groupID);
	}
	
	//Create a new group
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Groupe createGroup(@Valid @RequestBody Groupe groupe) {
		return groupeService.createGroup(groupe);
	}
	
	//Find a group by ID
	@GetMapping("/{id}")
	public Groupe getGroup(@PathVariable(name = "id") Integer groupID) {
		return groupeService.findGroupById(groupID);
	}
	
	//Update the group of the given ID by the newGroup in Request
	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping("/{id}")
	public Groupe updateGroup(@PathVariable(name = "id") Integer groupID, @Valid @RequestBody Groupe newGroup) {
		Groupe group = groupeService.findGroupById(groupID);
		group.setName(newGroup.getName());
		return groupeService.createGroup(group);
	}
	
	//Delete the group of given id in param
	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteGroup(@PathVariable(name = "id") Integer groupID){
		groupeService.deleteGroupe(groupID);
		return ResponseEntity.noContent().build();
	}
	

}
