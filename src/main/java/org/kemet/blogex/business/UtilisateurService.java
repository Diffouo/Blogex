package org.kemet.blogex.business;

import org.kemet.blogex.domain.Blog;
import org.kemet.blogex.domain.Commentaire;
import org.kemet.blogex.domain.Utilisateur;
import org.kemet.blogex.exceptionhandler.ResourceNotFound;
import org.kemet.blogex.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurService(UtilisateurRepository utilisateurRepository){
        this.utilisateurRepository = utilisateurRepository;
    }

    // Get the list of all users
    public List<Utilisateur> findAllUsers(){
        return utilisateurRepository.findAll();
    }

    // Get all artiles posted by a userID
    public List<Blog> findAllPostByUserID(Long id){
        Utilisateur user = utilisateurRepository.findById(id).orElseThrow(()
                -> new ResourceNotFound("User", "id", (int)id.longValue()));

        return user.getBlogs().stream().collect(Collectors.toList());
    }

    // Get all the comments from userID
    public List<Commentaire> findAllCommentsFromUserID(Long id){
        Utilisateur user = utilisateurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("User", "id", (int)id.longValue()));
        return user.getComments().stream().collect(Collectors.toList());
    }

    // Find a user by id
    public Utilisateur findUserById(Long id){
        return utilisateurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("User", "id", (int)id.longValue()));
    }

    // Create a new user from
    public Utilisateur createUser(Utilisateur utilisateur){
        utilisateur.setSignupDate(Calendar.getInstance().getTime());
        return utilisateurRepository.save(utilisateur);
    }

    // Update a user
    public Utilisateur updateUser(Long id, Utilisateur newUser){
        Utilisateur userFind =  utilisateurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("User", "id", (int)id.longValue()));

        userFind.setNom(newUser.getNom());
        userFind.setPrenom(newUser.getPrenom());
        userFind.setMail(newUser.getMail());
        userFind.setPhone(newUser.getPhone());
        userFind.setPassword(newUser.getPassword());

        return utilisateurRepository.save(userFind);
    }

    // Delete a user
    public void deleteUser(Long id){
        Utilisateur user =  utilisateurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("User", "id", (int)id.longValue()));
        utilisateurRepository.delete(user);
    }

    // Login a user using email and password
    public Utilisateur loginUser(String account, String password){
        return utilisateurRepository.findByEmailAndPassword(account, password);
    }

}
