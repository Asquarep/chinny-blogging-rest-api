package com.asquarep.bloggingrestapi.service.impl;

import com.asquarep.bloggingrestapi.converter.Converter;
import com.asquarep.bloggingrestapi.dto.CommentDTO;
import com.asquarep.bloggingrestapi.exception.BadRequestException;
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
    private final PostRepository postRepository;
    private final Converter converter;


    @Override
    public ResponseEntity<String> likeOrUnlikePost(long postId, long readerId) {
        //find post
        Optional<Post> postCheck = postRepository.findById(postId);
        if (postCheck.isPresent()) {
            // find reader
            Optional<Reader> readerCheck = readerRepository.findById(readerId);
            if (readerCheck.isPresent()) {
                //find like
                Optional<Like> like = likeRepository.findLikeByPostAndReader(postCheck.get(), readerCheck.get());
                if (like.isEmpty()) {
                    Like like1 = new Like();
                    like1.setPost(postCheck.get());
                    like1.setReader(readerCheck.get());
                    return new ResponseEntity<String>(saveLike(postCheck, like1), HttpStatus.OK);
                } else {
                    return unlikePost(postId, readerId);
                }
            } else {
                throw new BadRequestException("No reader with such ID.");
            }
        }
        throw new BadRequestException("Post not found.");
    }

    @Override
    public ResponseEntity<String> unlikePost(long postId, long readerId) {
        var post = postRepository.findById(postId);
        if (post.isPresent()) {
            var reader = readerRepository.findById(readerId);
            if (reader.isPresent()) {
                Optional<Like> checkLike = likeRepository.findLikeByPostAndReader(post.get(), reader.get());
                if(checkLike.isPresent()){
                    var post1 = checkLike.get().getPost();
                    if (post1.getNumberOfLikes() > 0) {
                        post1.setNumberOfLikes(post1.getNumberOfLikes() - 1);
                        postRepository.save(post1);
                    }
                    likeRepository.delete(checkLike.get());
                    return new ResponseEntity<String>("Post with ID: " + post1.getPostId() + " unliked by reader with ID: " + checkLike.get().getReader().getReaderId(), HttpStatus.OK);
                } else {
                    throw new ResourceNotFoundException("Like not found, or reader not authorized.");
                }
            } else {
                throw new ResourceNotFoundException("Reader not found.");
            }
        }
        throw new ResourceNotFoundException("Post not found.");
    }

    @Override
    public ResponseEntity<CommentDTO> commentOnPost(long postId, CommentDTO commentDTO, long userId) {
        Optional<Reader> reader = readerRepository.findById(userId);
        if (reader.isPresent()) {
            Optional<Post> post = postRepository.findById(postId);
            if (post.isPresent()) {
                Comment comment = (Comment) converter.convertCommentorDTO(commentDTO);
                comment.setReader(reader.get());
                Optional<CommentDTO> commentDTO1 = saveCommentAndReturnDTO(post, comment);
                return new ResponseEntity<CommentDTO>(commentDTO1.get(), HttpStatus.ACCEPTED);

            } else {
                throw new ResourceNotFoundException("Post does not exist.");
            }
        } else {
            return commentOnPost(postId, commentDTO);
        }
    }

    @Override
    public ResponseEntity<CommentDTO> commentOnPost(long postId, CommentDTO commentDTO) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            Comment comment = (Comment) converter.convertCommentorDTO(commentDTO);
            Optional<CommentDTO> commentDTO1 = saveCommentAndReturnDTO(post, comment);
            return new ResponseEntity<CommentDTO>(commentDTO1.get(), HttpStatus.ACCEPTED);

        }
        throw new ResourceNotFoundException("Post does not exist.");
    }




    @Override
    public ResponseEntity<String> readerDeleteComment(long commentId, long readerId) {
        Optional<Comment> checkComment = commentRepository.findCommentByCommentIdAndReader(commentId, readerRepository.getById(readerId));
        if(checkComment.isPresent()){
        var post1 = checkComment.get().getPost();
            if (post1.getNumberOfComments() > 0) {
            post1.setNumberOfComments(post1.getNumberOfComments() - 1);
            postRepository.save(post1);
            }
            commentRepository.delete(checkComment.get());
            return new ResponseEntity<String>("Comment deleted successfully.", HttpStatus.OK);
            } else {
                throw new ResourceNotFoundException("Comment not found, or reader not authorized.");
        }
    }

    @Override
    public ResponseEntity<String> bloggerDeleteComment(long commentId, long bloggerId) {
        Optional<Comment> checkComment = commentRepository.findById(commentId);
        if(checkComment.isPresent() && checkComment.get().getPost().getPostedBy().getBlogId() == bloggerId){
            var post1 = checkComment.get().getPost();
            if (post1.getNumberOfComments() > 0) {
                post1.setNumberOfComments(post1.getNumberOfComments() - 1);
                postRepository.save(post1);
            }
            commentRepository.delete(checkComment.get());
            return new ResponseEntity<String>("Deleted successfully.", HttpStatus.OK);
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

    private String saveLike(Optional<Post> post, Like like) {
        var post1 = post.get();
        like.setPost(post1);
        post1.getLikes().add(like);
        likeRepository.save(like);
        post1.setNumberOfLikes(post1.getNumberOfLikes() + 1);
        postRepository.save(post1);
        return "Post with ID: " + post1.getPostId() + " liked by reader with ID: " + like.getReader().getReaderId();
    }
}
