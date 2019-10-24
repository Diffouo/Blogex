package org.kemet.blogex.business;

import org.kemet.blogex.domain.Groupe;
import org.kemet.blogex.domain.Utilisateur;
import org.kemet.blogex.exceptionhandler.ResourceNotFound;
import org.kemet.blogex.repository.GroupeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupeService {

    private final GroupeRepository groupeRepository;

    public GroupeService(GroupeRepository groupeRepository) {
        this.groupeRepository = groupeRepository;
    }

    //Service that return all groups
    public List<Groupe> findAllGroups(){
        return groupeRepository.findAll();
    }

    // Service that create a new group
    public Groupe createGroup(Groupe groupe){
        return groupeRepository.save(groupe);
    }

    // Service that modify a group with param ID in the new param Groupe Object
    public Groupe updateGroup(Groupe groupe){
        Groupe group = groupeRepository.findById(groupe.getGroupid()).orElseThrow(()
                -> new ResourceNotFound("Groupe", "id", groupe.getGroupid()));
        group.setGrouplabel(group.getGrouplabel());
        return groupeRepository.save(group);
    }

    public void deleteGroup(Integer id){
        Groupe group = groupeRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Groupe", "id", id));
        groupeRepository.delete(group);
    }

    public Groupe findGroupByID(Integer id){
        return groupeRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Groupe", "id", id));
    }

    // Service returning a List of users from group ID
    public List<Utilisateur> findUserByGroupID(Integer id){
        Groupe group = findGroupByID(id); // Will throw ResourceNotFound if there is no group with given id
        return group.getUsers().stream().collect(Collectors.toList());
    }
}
