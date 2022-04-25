package com.asquarep.bloggingrestapi.controller;

import com.asquarep.bloggingrestapi.dto.CommentDTO;
import com.asquarep.bloggingrestapi.dto.LikeDTO;
import com.asquarep.bloggingrestapi.model.Comment;
import com.asquarep.bloggingrestapi.model.Like;
import com.asquarep.bloggingrestapi.service.impl.ReviewServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class ReviewController {
    private final ReviewServiceImpl reviewService;

    @PostMapping("/api/post/like-post")
    public Like likePost(LikeDTO likeDTO) {
        return null;
    }

    @PostMapping("/api/post/{post-id}/{reader-id}/unlike")
    public void unlikePost(@PathVariable("post-id") long postId,
                           @PathVariable("reader-id") long readerId) {

    }

    @PostMapping("/api/post/create-comment")
    public Comment commentOnPost(CommentDTO commentDTO) {
        return null;
    }

    @DeleteMapping("/api/post/{comment-id}/{reader-id}/delete-comment")
    public ResponseEntity<String> readerDeleteComment(@PathVariable("comment-id") long commentId,
                                              @PathVariable("reader-id") long readerId) {
        return reviewService.readerDeleteComment(commentId, readerId);
    }

    @DeleteMapping("/api/post/{comment-id}/{blogger-id}/delete-comment")
    public ResponseEntity<String> bloggerDeleteComment(long commentId, long bloggerId) {
        return reviewService.bloggerDeleteComment(commentId, bloggerId);
    }

}
