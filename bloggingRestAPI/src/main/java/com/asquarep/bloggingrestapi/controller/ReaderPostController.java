package com.asquarep.bloggingrestapi.controller;

import com.asquarep.bloggingrestapi.dto.BloggerDTO;
import com.asquarep.bloggingrestapi.dto.CommunityDTO;
import com.asquarep.bloggingrestapi.dto.PostDTO;
import com.asquarep.bloggingrestapi.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class ReaderPostController {
    private final PostService postService;

    @GetMapping("/api/posts")
    public ResponseEntity<List<PostDTO>> getAllPosts(PostDTO postDTO) {
        return postService.getAllPosts();
    }

    @GetMapping("/api/blogs/{id}/posts")
    public ResponseEntity<List<PostDTO>> getAllPostsByBloggerId(@PathVariable("id") long bloggerId) {
        return postService.getAllPostsByBloggerId(bloggerId);
    }

    @GetMapping("/api/communities/{id}/posts")
    public ResponseEntity<List<PostDTO>> getAllPostsByCommunityId(@PathVariable("id") long communityId) {
        return postService.getAllPostsByCommunity(communityId);
    }
    @GetMapping("/api/posts/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable("id") long postId) {
        return postService.getPostById(postId);
    }

    @GetMapping("/api/communities")
    public ResponseEntity<List<CommunityDTO>> getAllCommunities() {
        return postService.getAllCommunities();
    }

    @GetMapping("/api/communities/{id}")
    public ResponseEntity<CommunityDTO> getCommunityById(@PathVariable("id") long communityId) {
        return postService.getCommunityById(communityId);
    }

    @GetMapping("/api/blogs/{id}")
    public ResponseEntity<BloggerDTO> getBloggerById(@PathVariable(name = "id") Long blogId){
        return postService.getBlogById(blogId);
    }
    @GetMapping("/api/blogs")
    public ResponseEntity<List<BloggerDTO>> getAllBlogs(){
        return postService.getAllBlogs();
    }
}
