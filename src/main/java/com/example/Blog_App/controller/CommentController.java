package com.example.Blog_App.controller;

import com.example.Blog_App.bean.Comment;
import com.example.Blog_App.bean.Post;
import com.example.Blog_App.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/posts/{id}/addComment")
    public ResponseEntity<String> addComment(@PathVariable Long id, @RequestBody Comment comment) {
        Comment response = commentService.addComment(comment, id);
        return new ResponseEntity<>("Comment created succesfully, ID ->" + response.getId(), HttpStatus.CREATED);
    }

    @GetMapping("/getComment/{id}")
    public ResponseEntity<Comment> getCommentByCommentID(@PathVariable Long id) {
        Comment response = commentService.getCommentByCommentID(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/post/{postId}/getComment")
    public List<Comment> getCommentByPostID(@PathVariable Long postId) {
        return commentService.getCommentByPostID(postId);
    }

    @PutMapping("/post/{postId}/updateComment/{commentId}")
    public ResponseEntity<String> updateComment(@PathVariable Long commentId,@PathVariable Long postId, @RequestBody Comment comment) {
        commentService.updateCommentByCommentID(postId, commentId, comment);
        return new ResponseEntity<>("Comment updated successfully.", HttpStatus.OK);
    }

    @DeleteMapping("/deleteComment/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable long id) {
        commentService.deleteCommentByCommentID(id);
        return new ResponseEntity<>("Comment Deleted successfully", HttpStatus.OK);
    }

}
