package com.asquarep.bloggingrestapi.controller;

import com.asquarep.bloggingrestapi.dto.CommunityDTO;
import com.asquarep.bloggingrestapi.dto.PostDTO;
import com.asquarep.bloggingrestapi.exception.BadRequestException;
import com.asquarep.bloggingrestapi.model.Blogger;
import com.asquarep.bloggingrestapi.model.Community;
import com.asquarep.bloggingrestapi.model.Post;
import com.asquarep.bloggingrestapi.service.impl.BloggerServiceImpl;
import com.asquarep.bloggingrestapi.service.impl.PostServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@RestController
public class BloggerPostController {
    private final BloggerServiceImpl blogService;
    private final PostServiceImpl postService;


    @GetMapping("/api/blogs")
    public List<Blogger> getAllBlogs(){
        return blogService.getAllBlogs();
    }


    @PostMapping("/api/community/create-community/{blogger-id}")
    public ResponseEntity<String> createCommunity(@RequestBody CommunityDTO communityDTO, @PathVariable(value = "blogger-id") long bloggerId) {
        Community community = postService.createCommunity(communityDTO, bloggerId);
        if (community != null) {
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
    public ResponseEntity<String> createPost(@RequestBody PostDTO postDTO, @PathVariable("blogger-id") long bloggerId) {

        Optional<Post> createdPost =  postService.createPost(postDTO,bloggerId);
        if (createdPost.isEmpty()) {
            throw new BadRequestException("Only registered bloggers can create posts.");
        }
        return new ResponseEntity<String>("Post created successfully", HttpStatus.CREATED);
    }

    @PutMapping()
    public Post editPost(long postId, long bloggerId) {
        return null;
    }

    @DeleteMapping("/api/blogs/{blogger-id}/posts/{post-id}/delete")
    public void deletePostById(@PathVariable("blogger-id") long postId, @PathVariable("post-id") long bloggerId) {

    }

    @DeleteMapping("/api/blogs/{blogger-id}/posts/delete-all")
    public void deleteAllPostsByBloggerId(@PathVariable("blogger-id") long bloggerId) {

    }
}
