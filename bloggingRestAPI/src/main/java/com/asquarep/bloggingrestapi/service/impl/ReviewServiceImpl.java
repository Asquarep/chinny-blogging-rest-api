package com.asquarep.bloggingrestapi.service.impl;

import com.asquarep.bloggingrestapi.dto.CommentDTO;
import com.asquarep.bloggingrestapi.dto.LikeDTO;
import com.asquarep.bloggingrestapi.model.Comment;
import com.asquarep.bloggingrestapi.model.Like;
import com.asquarep.bloggingrestapi.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {
    @Override
    public Like likePost(LikeDTO likeDTO, long postId) {
        return null;
    }

    @Override
    public void unlikePost(long postId, long readerId) {

    }

    @Override
    public Comment commentOnPost(CommentDTO commentDTO, long postId) {
        return null;
    }

    @Override
    public void readerDeleteComment(long commentId, long readerId) {

    }

    @Override
    public void bloggerDeleteComment(long commentId, long bloggerId) {

    }
}
