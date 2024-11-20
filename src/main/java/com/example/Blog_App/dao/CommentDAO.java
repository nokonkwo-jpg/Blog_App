package com.example.Blog_App.dao;

import com.example.Blog_App.bean.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDAO extends JpaRepository<Comment, Long> {

    public List<Comment> findByPostId(Long postId);
}
