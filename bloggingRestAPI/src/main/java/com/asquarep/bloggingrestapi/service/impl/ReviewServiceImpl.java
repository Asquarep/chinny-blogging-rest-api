package com.asquarep.bloggingrestapi.service.impl;

import com.asquarep.bloggingrestapi.dto.CommentDTO;
import com.asquarep.bloggingrestapi.dto.LikeDTO;
import com.asquarep.bloggingrestapi.model.Comment;
import com.asquarep.bloggingrestapi.model.Like;
import com.asquarep.bloggingrestapi.repository.*;
import com.asquarep.bloggingrestapi.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {
    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;
    private final ReaderRepository readerRepository;
    private final BloggerRepository bloggerRepository;


    @Override
    public Like likePost(LikeDTO likeDTO) {
        return null;
    }

    @Override
    public void unlikePost(long postId, long readerId) {

    }

    @Override
    public Comment commentOnPost(CommentDTO commentDTO) {
        return null;
    }

    @Override
    public void readerDeleteComment(long commentId, long readerId) {

    }

    @Override
    public void bloggerDeleteComment(long commentId, long bloggerId) {

    }
}
