package org.kemet.blogex.restapi;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.kemet.blogex.entity.Groupe;
import org.kemet.blogex.entity.Utilisateur;
import org.kemet.blogex.exceptionhandler.ResourceNotFound;
import org.kemet.blogex.repository.GroupeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/groups")
public class GroupeREST {
	
	@Autowired
	private GroupeRepository groupRepo;
	
	//Getting all the groups
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<Groupe> getAllGroup(){
		return groupRepo.findAll();
	}
	
	//Get all the users of the given groupID
	@RequestMapping(path = "/{id}/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Utilisateur> getUserByGroupe(@PathVariable(name = "id") Integer groupID){
		Groupe groupe = groupRepo.findById(groupID).orElseThrow(()->new ResourceNotFound("Utilisateur du groupe", "id", groupID.intValue()));
		List<Utilisateur> users = new ArrayList<>();
		for(Utilisateur user: groupe.getUsers()) {
			users.add(user);
		}
		return users;
	}
	
	//Create a new group
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Groupe createGroup(@Valid @RequestBody Groupe group) {
		return groupRepo.save(group);
	}
	
	//Find a group by ID
	@RequestMapping(path = "/groups/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Groupe getGroup(@PathVariable(value = "id") Integer groupid) {
		return groupRepo.findById(groupid).orElseThrow(() -> new ResourceNotFound("Groupe", "groupid", groupid.intValue()));
	}
	
	//Update the group of the given ID by the newGroup in Request
	@RequestMapping(path = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Groupe updateGroup(@PathVariable(name = "id") Integer groupid, @Valid @RequestBody Groupe newGroup) {
		Groupe group = groupRepo.findById(groupid).orElseThrow(() -> new ResourceNotFound("Groupe", "groupid", groupid.intValue()));
		group.setGrouplabel(newGroup.getGrouplabel());
		return groupRepo.save(group);
	}
	
	//Delete the group of given id in param
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteGroup(@PathVariable(name = "id") Integer groupid){
		Groupe group = groupRepo.findById(groupid).orElseThrow(() -> new ResourceNotFound("Groupe", "groupid", groupid.intValue()));
		groupRepo.delete(group);
		return ResponseEntity.ok().build();
	}
	

}
