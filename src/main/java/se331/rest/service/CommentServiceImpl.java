package se331.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se331.rest.dao.CommentDao;
import se331.rest.entity.Comment;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentDao comnentDao;
    @Override
    public Comment save(Comment comment){
        return comnentDao.save(comment);
    }

    @Override
    public List<Comment> getAllComment() {return comnentDao.getComment(Pageable.unpaged()).getContent();}
}
