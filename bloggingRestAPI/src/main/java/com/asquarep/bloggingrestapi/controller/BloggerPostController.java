package com.asquarep.bloggingrestapi.controller;

import com.asquarep.bloggingrestapi.dto.CommentDTO;
import com.asquarep.bloggingrestapi.dto.CommunityDTO;
import com.asquarep.bloggingrestapi.dto.PostDTO;
import com.asquarep.bloggingrestapi.exception.BadRequestException;
import com.asquarep.bloggingrestapi.exception.ResourceNotFoundException;
import com.asquarep.bloggingrestapi.service.impl.BloggerAccountServiceImpl;
import com.asquarep.bloggingrestapi.service.impl.PostServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@AllArgsConstructor
@RestController
public class BloggerPostController {
    private final BloggerAccountServiceImpl blogService;
    private final PostServiceImpl postService;



    @PostMapping("/api/community/create-community/{blogger-id}")
    public ResponseEntity<String> createCommunity(@RequestBody CommunityDTO communityDTO, @PathVariable(value = "blogger-id") long bloggerId) {
        Optional<CommunityDTO> community = postService.createCommunity(communityDTO, bloggerId);
        if (community.isPresent()) {
            return new ResponseEntity<String>("Created Successfully", HttpStatus.CREATED);
        }else{
            throw new BadRequestException("Not authorized to perform operation, or community already exists");
        }
    }


//    @GetMapping("/api/blogs/{id}")
//    public ResponseEntity<Blogger> getBlogById(@PathVariable(name = "id") Long blogId){
//        Optional<Blogger> blogger = blogService.getBlogById(blogId);
//        return blogger.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>((Blogger) null, HttpStatus.NOT_FOUND));
//
//    }

    @PostMapping("/api/blog/create-post/{blogger-id}")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO, @PathVariable("blogger-id") long bloggerId) {

        Optional<PostDTO> createdPost =  postService.createPost(postDTO,bloggerId);
        if (createdPost.isEmpty()) {
            throw new BadRequestException("Only registered bloggers can create posts.");
        }
        return new ResponseEntity<PostDTO>(createdPost.get(), HttpStatus.CREATED);
    }

    @PutMapping("/api/blog/edit-post/{post-id}/{blogger-id}")
    public ResponseEntity<PostDTO> editPost(@RequestBody PostDTO postDTO,
                                         @PathVariable("post-id") long postId, @PathVariable("blogger-id") long bloggerId) {
        Optional<PostDTO> editedPost = postService.editPost(postDTO, postId, bloggerId);
        if (editedPost.isEmpty()) {
            throw new BadRequestException("Only registered bloggers can edit post");
        }
        return new ResponseEntity<PostDTO>(editedPost.get(), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/api/blogs/post/{blogger-id}/{post-id}/delete")
    public ResponseEntity<String> deletePostById(@PathVariable("post-id") long postId,
                               @PathVariable("blogger-id") long bloggerId) {
        return new ResponseEntity<String>(postService.deletePostById(postId, bloggerId), HttpStatus.CREATED);

    }

    @DeleteMapping("/api/blogs/{blogger-id}/posts/delete-all")
    public ResponseEntity<String> deleteAllPostsByBloggerId(@PathVariable("blogger-id") long bloggerId) {
        return new ResponseEntity<String>(postService.deleteAllPostsByBloggerId(bloggerId), HttpStatus.OK);
    }


}
