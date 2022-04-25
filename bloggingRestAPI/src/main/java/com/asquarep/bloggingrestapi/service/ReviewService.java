package com.asquarep.bloggingrestapi.service;

import com.asquarep.bloggingrestapi.dto.CommentDTO;
import com.asquarep.bloggingrestapi.dto.LikeDTO;
import com.asquarep.bloggingrestapi.dto.PostDTO;
import com.asquarep.bloggingrestapi.model.Comment;
import com.asquarep.bloggingrestapi.model.Like;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface ReviewService {
    Optional<LikeDTO> likePost(LikeDTO likeDTO);
    Optional<String> unlikePost(long postId, long readerId);

    Optional<CommentDTO> commentOnPost(long postId, CommentDTO commentDTO, long userId);
    Optional<CommentDTO> commentOnPost(long postId, CommentDTO commentDTO);

    ResponseEntity<String> readerDeleteComment(long commentId, long readerId);
    ResponseEntity<String> bloggerDeleteComment(long commentId, long bloggerId);

}
