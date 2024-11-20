package com.example.Blog_App.service;

import com.example.Blog_App.bean.Comment;
import com.example.Blog_App.bean.Post;
import com.example.Blog_App.dao.CommentDAO;
import com.example.Blog_App.dao.PostDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private PostDAO postDAO;

    @Autowired
    private CommentDAO commentDAO;

    public Comment addComment(Comment comment, Long postId) {
        Post post = postDAO.findById(postId).orElseThrow(() -> new RuntimeException(postId + "-> this post id doesn't exist"));
        comment.setPost(post);

        return commentDAO.save(comment);
    }

    public Comment getCommentByCommentID(Long commentId) {
            return commentDAO.findById(commentId).orElseThrow(() -> new RuntimeException(commentId + "-> this comment id doesn't exist"));

    }

    public List<Comment> getCommentByPostID(Long postId) {
        return commentDAO.findByPostId(postId);
    }

    public void updateCommentByCommentID(Long postId, Long commentId, Comment comment) {
        Post post = postDAO.findById(postId).orElseThrow(() -> new RuntimeException(postId + "-> this post id doesn't exist"));
        comment.setId(commentId);
        comment.setPost(post);
        commentDAO.save(comment);
    }

    public void deleteCommentByCommentID(Long commentId) {
        if(commentDAO.findById(commentId).isPresent()) {
            commentDAO.deleteById(commentId);
        } else {
            throw new RuntimeException(commentId + "-> this comment id doesn't exist");
        }
    }
    //addComment()
    //getCommentByCommentID()
    //getCommentByPostID()
    //updateComment()
    //deleteComment()
}
