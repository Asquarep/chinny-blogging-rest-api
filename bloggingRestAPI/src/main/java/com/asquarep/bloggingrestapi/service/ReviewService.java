package com.asquarep.bloggingrestapi.service;

import com.asquarep.bloggingrestapi.dto.CommentDTO;
import org.springframework.http.ResponseEntity;

public interface ReviewService {
    ResponseEntity<String> likeOrUnlikePost(long postId, long readerId);
    ResponseEntity<String> unlikePost(long postId, long readerId);

    ResponseEntity<CommentDTO> commentOnPost(long postId, CommentDTO commentDTO, long userId);
    ResponseEntity<CommentDTO> commentOnPost(long postId, CommentDTO commentDTO);

    ResponseEntity<String> readerDeleteComment(long commentId, long readerId);
    ResponseEntity<String> bloggerDeleteComment(long commentId, long bloggerId);

}
