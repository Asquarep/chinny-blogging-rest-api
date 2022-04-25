package com.asquarep.bloggingrestapi.service.impl;

import com.asquarep.bloggingrestapi.converter.Converter;
import com.asquarep.bloggingrestapi.dto.CommentDTO;
import com.asquarep.bloggingrestapi.dto.LikeDTO;
import com.asquarep.bloggingrestapi.exception.ResourceNotFoundException;
import com.asquarep.bloggingrestapi.model.*;
import com.asquarep.bloggingrestapi.repository.*;
import com.asquarep.bloggingrestapi.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {
    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;
    private final ReaderRepository readerRepository;
    private final BloggerRepository bloggerRepository;
    private final PostRepository postRepository;
    private final Converter converter;


    @Override
    public Optional<LikeDTO> likePost(LikeDTO likeDTO) {
        return null;
    }

    @Override
    public Optional<String> unlikePost(long postId, long readerId) {
        return null;
    }

    @Override
    public Optional<CommentDTO> commentOnPost(long postId, CommentDTO commentDTO, long userId) {
        Optional<Reader> reader = readerRepository.findById(userId);
        if (reader.isPresent()) {
            Optional<Post> post = postRepository.findById(postId);
            if (post.isPresent()) {
                Comment comment = (Comment) converter.convertCommentorDTO(commentDTO);
                comment.setReader(reader.get());
                return saveCommentAndReturnDTO(post, comment);
            } else {
                return Optional.empty();
            }
        } else {
            return commentOnPost(postId, commentDTO);
        }
    }

    @Override
    public Optional<CommentDTO> commentOnPost(long postId, CommentDTO commentDTO) {

        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            Comment comment = (Comment) converter.convertCommentorDTO(commentDTO);
            return saveCommentAndReturnDTO(post, comment);
        }
        return Optional.empty();
    }




    @Override
    public ResponseEntity<String> readerDeleteComment(long commentId, long readerId) {
        Optional<Comment> checkComment = commentRepository.findCommentByCommentIdAndReader(commentId, readerRepository.getById(readerId));
        if(checkComment.isPresent()){
            commentRepository.delete(checkComment.get());
            return new ResponseEntity<String>(String.valueOf(Optional.of("Deleted successfully.")), HttpStatus.OK);
            } else {
                throw new ResourceNotFoundException("Comment not found, or reader not authorized.");
        }
    }

    @Override
    public ResponseEntity<String> bloggerDeleteComment(long commentId, long bloggerId) {
        Optional<Comment> checkComment = commentRepository.findById(commentId);
        if(checkComment.isPresent() && checkComment.get().getPost().getPostedBy().getBlogId() == bloggerId){
            commentRepository.delete(checkComment.get());
            return new ResponseEntity<String>(String.valueOf(Optional.of("Deleted successfully.")), HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Comment not found, or blogger not authorized.");
        }
    }

    private Optional<CommentDTO> saveCommentAndReturnDTO(Optional<Post> post, Comment comment) {
        var post1 = post.get();
        comment.setPost(post1);
        post1.getComments().add(comment);
        commentRepository.save(comment);
        post1.setNumberOfComments(post1.getNumberOfComments() + 1);
        postRepository.save(post1);

        CommentDTO commentDTO1 = (CommentDTO) converter.convertCommentorDTO(comment);
        return Optional.of(commentDTO1);
    }

    private Optional<LikeDTO> saveLikeAndReturnDTO(Optional<Post> post, Like like) {
        var post1 = post.get();
        like.setPost(post1);
        post1.getLikes().add(like);
        likeRepository.save(like);
        post1.setNumberOfLikes(post1.getNumberOfLikes() + 1);
        postRepository.save(post1);

        LikeDTO likeDTO = (LikeDTO) converter.convertCommentorDTO(like);
        return Optional.of(likeDTO);
    }
}
