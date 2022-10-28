package se331.rest.dao;

import  org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.data.domain.Page;
import  org.springframework.data.domain.Pageable;
import  org.springframework.stereotype.Repository;
import se331.rest.entity.Comment;
import se331.rest.repository.CommentRepository;

@Repository
public class CommentDaolmpl implements CommentDao {
    @Autowired
    CommentRepository coommentrepository;
    @Override
    public Comment save(Comment comment){
        return coommentrepository.save(comment);
    }

    @Override
    public Page<Comment> getComment(Pageable pageRequest){
        return coommentrepository.findAll(pageRequest);
    }

}
