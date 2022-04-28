package com.asquarep.bloggingrestapi.controller;

import com.asquarep.bloggingrestapi.dto.CommentDTO;
import com.asquarep.bloggingrestapi.exception.ResourceNotFoundException;
import com.asquarep.bloggingrestapi.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
@RestController
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/api/post/{post-id}/like-unlike-post/{reader-id}")
    public ResponseEntity<String> likeOrUnlikePost(@PathVariable("post-id") long postId, @PathVariable("reader-id") long readerId) {
        return reviewService.likeOrUnlikePost(postId, readerId);
    }


    @PostMapping("/api/post/comment/create-comment/{post-id}/{reader-id}")
    public ResponseEntity<CommentDTO> commentOnPost(@PathVariable("post-id") long postId, @RequestBody CommentDTO commentDTO, @PathVariable("reader-id") long readerId) {
        return reviewService.commentOnPost(postId, commentDTO, readerId);
    }

    @PostMapping("/api/post/comment/create-comment/{post-id}")
    public ResponseEntity<CommentDTO> commentOnPost(@PathVariable("post-id") long postId, @RequestBody CommentDTO commentDTO) {

        return reviewService.commentOnPost(postId, commentDTO);
    }


    @DeleteMapping("/api/post/comment/{comment-id}/{reader-id}/reader-delete-comment")
    public ResponseEntity<String> readerDeleteComment(@PathVariable("comment-id") long commentId,
                                              @PathVariable("reader-id") long readerId) {
        return reviewService.readerDeleteComment(commentId, readerId);
    }

    @DeleteMapping("/api/post/comment/{comment-id}/{blogger-id}/blogger-delete-comment")
    public ResponseEntity<String> bloggerDeleteComment(@PathVariable("comment-id") long commentId, @PathVariable("blogger-id") long bloggerId) {
        return reviewService.bloggerDeleteComment(commentId, bloggerId);
    }

}
