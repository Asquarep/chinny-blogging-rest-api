package com.asquarep.bloggingrestapi.controller;

import com.asquarep.bloggingrestapi.dto.PostDTO;
import com.asquarep.bloggingrestapi.model.Blogger;
import com.asquarep.bloggingrestapi.model.Community;
import com.asquarep.bloggingrestapi.model.Post;
import com.asquarep.bloggingrestapi.service.impl.BloggerServiceImpl;
import com.asquarep.bloggingrestapi.service.impl.PostServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
public class ReaderPostController {
    private final PostServiceImpl postService;
    private final BloggerServiceImpl bloggerService;

    @GetMapping("/api/posts")
    public List<Post> getAllPosts(PostDTO postDTO) {
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
    public List<Post> getPostById(@PathVariable("id") long postId) {
        return null;
    }


    @GetMapping("/api/communities")
    public List<Community> getAllCommunities() {
        return postService.getAllComunities();
    }

    @GetMapping("/api/communities/{id}")
    public List<Post> getCommunityById(@PathVariable("id") long communityId) {
        return null;
    }

    @GetMapping("/api/blogs/{id}")
    public ResponseEntity<Blogger> getBloggerById(@PathVariable(name = "id") Long blogId){
        Optional<Blogger> blogger = bloggerService.getBlogById(blogId);
        return blogger.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>((Blogger) null, HttpStatus.NOT_FOUND));

    }


}
