package com.asquarep.bloggingrestapi.controller;

import com.asquarep.bloggingrestapi.model.Blogger;
import com.asquarep.bloggingrestapi.service.impl.BloggerServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class BlogController {
    private final BloggerServiceImpl blogService;

    public BlogController(BloggerServiceImpl blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/api/blogs")
    public List<Blogger> getAllBlogs(){
        return blogService.getAllBlogs();
    }

    @GetMapping("/api/blogs/{id}")
    public ResponseEntity<Blogger> getBlogById(@PathVariable(name = "id") Long blogId){
        Optional<Blogger> blogger = blogService.getBlogById(blogId);
        return blogger.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>((Blogger) null, HttpStatus.NOT_FOUND));

    }
}
