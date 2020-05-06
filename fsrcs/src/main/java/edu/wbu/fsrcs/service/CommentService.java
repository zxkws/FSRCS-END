package edu.wbu.fsrcs.service;

import edu.wbu.fsrcs.dao.CommentDao;
import edu.wbu.fsrcs.entity.Comment;
import edu.wbu.fsrcs.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentDao commentDao;

    public List<Comment> queryComments(String remedyId){
        return commentDao.queryById(remedyId);
    }

    public void insertComment(Comment comment){
        comment.setCommentId(new IdWorker().nextId());
        comment.setCreateDate(new Date(System.currentTimeMillis()));
        commentDao.insert(comment);
    }
}
