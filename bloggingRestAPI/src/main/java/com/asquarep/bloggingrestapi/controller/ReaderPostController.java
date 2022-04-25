package com.asquarep.bloggingrestapi.controller;

import com.asquarep.bloggingrestapi.dto.BloggerDTO;
import com.asquarep.bloggingrestapi.dto.CommentDTO;
import com.asquarep.bloggingrestapi.dto.CommunityDTO;
import com.asquarep.bloggingrestapi.dto.PostDTO;
import com.asquarep.bloggingrestapi.exception.ResourceNotFoundException;
import com.asquarep.bloggingrestapi.model.Post;
import com.asquarep.bloggingrestapi.service.impl.BloggerAccountServiceImpl;
import com.asquarep.bloggingrestapi.service.impl.PostServiceImpl;
import com.asquarep.bloggingrestapi.service.impl.ReviewServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
public class ReaderPostController {
    private final PostServiceImpl postService;
    private final BloggerAccountServiceImpl bloggerService;
    private final ReviewServiceImpl reviewService;

    @GetMapping("/api/posts")
    public List<PostDTO> getAllPosts(PostDTO postDTO) {
        return postService.getAllPosts();
    }

    @GetMapping("/api/blogs/{id}/posts")
    public List<Post> getAllPostsByBloggerId(@PathVariable("id") long bloggerId) {
        return postService.getAllPostsByBloggerId(bloggerId);
    }

    @GetMapping("/api/communities/{id}/posts")
    public List<Post> getAllPostsByCommunityId(@PathVariable("id") long communityId) {
        return null;
    }
    @GetMapping("/api/posts/{id}")
    public Optional<PostDTO> getPostById(@PathVariable("id") long postId) {
        return postService.getPostById(postId);
    }

    @GetMapping("/api/communities")
    public List<CommunityDTO> getAllCommunities() {
        return postService.getAllCommunities();
    }

    @GetMapping("/api/communities/{id}")
    public ResponseEntity<CommunityDTO> getCommunityById(@PathVariable("id") long communityId) {
        var communityById = postService.getCommunityById(communityId);
        if (communityById.isEmpty()) {
            throw new ResourceNotFoundException("Community not found");
        }
        return new ResponseEntity<CommunityDTO>(communityById.get(), HttpStatus.OK);
    }

    @GetMapping("/api/blogs/{id}")
    public ResponseEntity<BloggerDTO> getBloggerById(@PathVariable(name = "id") Long blogId){
        Optional<BloggerDTO> blogger = postService.getBlogById(blogId);
        if (blogger.isEmpty()) {
            throw new ResourceNotFoundException("Blogger not found");
        }
        return new ResponseEntity<BloggerDTO>(blogger.get(), HttpStatus.OK);
    }
    @GetMapping("/api/blogs")
    public List<BloggerDTO> getAllBlogs(){
        return postService.getAllBlogs();
    }

    @PostMapping("/api/post/{post-id}/comment/{reader-id}")
    public ResponseEntity<CommentDTO> commentOnPost(@PathVariable("post-id") long postId, @RequestBody CommentDTO commentDTO, @PathVariable("reader-id") long readerId) {
        Optional<CommentDTO> commentDTO1 = reviewService.commentOnPost(postId, commentDTO, readerId);
        if (commentDTO1.isEmpty()) {
            throw new ResourceNotFoundException("Post doesn't exist in blog");
        }
        return new ResponseEntity<CommentDTO>(commentDTO1.get(), HttpStatus.ACCEPTED);
    }

    @PostMapping("/api/post/{post-id}/comment")
    public ResponseEntity<CommentDTO> commentOnPost(@PathVariable("post-id") long postId, @RequestBody CommentDTO commentDTO) {

        Optional<CommentDTO> commentDTO1 = reviewService.commentOnPost(postId, commentDTO);
        if (commentDTO1.isEmpty()) {
            throw new ResourceNotFoundException("Post doesn't exist in blog");
        }
        return new ResponseEntity<CommentDTO>(commentDTO1.get(), HttpStatus.ACCEPTED);
    }

}
