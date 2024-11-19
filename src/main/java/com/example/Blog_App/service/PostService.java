package com.example.Blog_App.service;

import com.example.Blog_App.bean.Post;
import com.example.Blog_App.dao.PostDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostDAO postDAO;

    public Post createPost(Post post) {
        Post newPost = postDAO.save(post);
        return newPost;
    }

    public Post getPostbyID(Long id) {
        return postDAO.findById(id).orElseThrow(() -> new RuntimeException("No Post found with ID: " + id));
    }

    public List<Post> getAllPosts() {
        return postDAO.findAll();
    }

    public void updatePostByID(Post post, Long id) {
        if(postDAO.findById(id).isPresent()){
            Post newPost = new Post();
            newPost.setId(id);
            newPost.setTitle(post.getTitle());
            newPost.setDescription(post.getDescription());

            postDAO.save(newPost);
        } else{
            throw new RuntimeException("No Post found with ID: " + id);
        }
    }

    public void deletePostByID(Long id) {
        if(postDAO.findById(id).isPresent()) {
            postDAO.deleteById(id);
        } else{
            throw new RuntimeException("No Post found with ID: " + id);
        }
    }
}
