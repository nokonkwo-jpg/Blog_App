package com.example.Blog_App.controller;

import com.example.Blog_App.bean.Post;
import com.example.Blog_App.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/createPost")
    public ResponseEntity<String> createPost(@RequestBody Post post) {
        Post response = postService.createPost(post);

        return new ResponseEntity<>("Post Created successfully. ID ->" + response.getId(), HttpStatus.CREATED);
    }

    @GetMapping("/getPost/{id}")
    public ResponseEntity<Post> getPostByID(@PathVariable long id) {
        Post response = postService.getPostbyID(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAllPosts")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @PutMapping("/updatePost/{id}")
    public ResponseEntity<String> updatePost(@PathVariable long id, @RequestBody Post post) {
        postService.updatePostByID(post, id);
        return new ResponseEntity<>("Post Updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/deletePost/{id}")
    public ResponseEntity<String> deletePost(@PathVariable long id) {
        postService.deletePostByID(id);
        return new ResponseEntity<>("Post Deleted successfully", HttpStatus.OK);
    }
}
