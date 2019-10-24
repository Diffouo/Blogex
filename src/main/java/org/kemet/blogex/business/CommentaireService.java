package org.kemet.blogex.business;

import org.kemet.blogex.domain.Blog;
import org.kemet.blogex.domain.Commentaire;
import org.kemet.blogex.exceptionhandler.ResourceNotFound;
import org.kemet.blogex.repository.BlogRepository;
import org.kemet.blogex.repository.CommentaireRepository;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class CommentaireService {

    private final CommentaireRepository commentaireRepository;
    private final BlogRepository blogRepository;

    public CommentaireService(CommentaireRepository commentaireRepository, BlogRepository blogRepository) {
        this.commentaireRepository = commentaireRepository;
        this.blogRepository = blogRepository;
    }

    //---Find all comments
    public List<Commentaire> findAllComments(){
        return commentaireRepository.findAll();
    }

    // Create a new comment
    public Commentaire createComment(Long blogID, Commentaire comment){
        Blog blog = blogRepository.findById(blogID).orElseThrow(() -> new ResourceNotFound("Blog", "id", (int)blogID.longValue()));
        comment.setBlog(blog);
        comment.setDateCmt(Calendar.getInstance().getTime());

        return commentaireRepository.save(comment);
    }

    //--- Update a comment of given ID
    public Commentaire updateComment(Long id, Commentaire newComment){
        Commentaire commentaire = commentaireRepository.findById(id)
                .orElseThrow( ()-> new ResourceNotFound("Commentaire", "id", (int)id.longValue()));

        commentaire.setText(newComment.getText());
        commentaire.setDateCmt(Calendar.getInstance().getTime());
        commentaire.setCommentby(newComment.getCommentby());

        return commentaireRepository.save(commentaire);
    }

    //--- delete a comment
    public void deleteComment(Long id){
        Commentaire commentaire = commentaireRepository.findById(id)
                .orElseThrow( ()-> new ResourceNotFound("Commentaire", "id", (int)id.longValue()));

        commentaireRepository.delete(commentaire);
    }
}
