package com.asquarep.bloggingrestapi.service;

import com.asquarep.bloggingrestapi.dto.CommentDTO;
import com.asquarep.bloggingrestapi.dto.LikeDTO;
import com.asquarep.bloggingrestapi.dto.PostDTO;
import com.asquarep.bloggingrestapi.model.Comment;
import com.asquarep.bloggingrestapi.model.Like;

public interface ReviewService {
    Like likePost(LikeDTO likeDTO, long postId);
    void unlikePost(long postId, long readerId);
    Comment commentOnPost(CommentDTO commentDTO, long postId);
    void readerDeleteComment(long commentId, long readerId);
    void bloggerDeleteComment(long commentId, long bloggerId);

}
