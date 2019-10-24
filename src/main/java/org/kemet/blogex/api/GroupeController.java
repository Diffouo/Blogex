package org.kemet.blogex.api;

import java.util.List;

import javax.validation.Valid;

import org.kemet.blogex.business.GroupeService;
import org.kemet.blogex.domain.Groupe;
import org.kemet.blogex.domain.Utilisateur;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
		return groupeService.findAllGroups();
	}
	
	//Get all the users of the given groupID
	@GetMapping("/{id}/users")
	public List<Utilisateur> getUserByGroupe(@PathVariable(name = "id") Integer groupID){
		return groupeService.findUserByGroupID(groupID);
	}
	
	//Create a new group
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Groupe createGroup(@Valid @RequestBody Groupe group) {
		return groupeService.createGroup(group);
	}
	
	//Find a group by ID
	@GetMapping("/groups/{id}")
	public Groupe findGroupById(@PathVariable(value = "id") Integer groupID) {
		return groupeService.findGroupByID(groupID);
	}
	
	//Update the group of the given ID by the newGroup in Request
	@PutMapping("/{id}")
	public Groupe updateGroup(@PathVariable(name = "id") Integer groupID, @Valid @RequestBody Groupe newGroup) {
		return groupeService.updateGroup(newGroup);
	}
	
	//Delete the group of given id in param
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteGroup(@PathVariable(name = "id") Integer groupID){
		groupeService.deleteGroup(groupID);
		return ResponseEntity.noContent().build();
	}
	

}
