package com.asquarep.bloggingrestapi.controller;

import com.asquarep.bloggingrestapi.dto.CommunityDTO;
import com.asquarep.bloggingrestapi.dto.PostDTO;
import com.asquarep.bloggingrestapi.exception.BadRequestException;
import com.asquarep.bloggingrestapi.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@AllArgsConstructor
@RestController
public class BloggerPostController {
    private final PostService postService;


    @PostMapping("/api/community/create-community/{blogger-id}")
    public ResponseEntity<CommunityDTO> createCommunity(@RequestBody CommunityDTO communityDTO, @PathVariable(value = "blogger-id") long bloggerId) {
        return postService.createCommunity(communityDTO, bloggerId);
    }

    @PostMapping("/api/blog/create-post/{blogger-id}")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO, @PathVariable("blogger-id") long bloggerId) {
        return postService.createPost(postDTO, bloggerId);
    }

    @PutMapping("/api/blog/edit-post/{post-id}/{blogger-id}")
    public ResponseEntity<PostDTO> editPost(@RequestBody PostDTO postDTO,
                                         @PathVariable("post-id") long postId, @PathVariable("blogger-id") long bloggerId) {
        return postService.editPost(postDTO, postId, bloggerId);
    }

    @DeleteMapping("/api/blogs/post/{blogger-id}/{post-id}/delete")
    public ResponseEntity<String> deletePostById(@PathVariable("post-id") long postId,
                               @PathVariable("blogger-id") long bloggerId) {
        return postService.deletePostById(postId, bloggerId);

    }

    @DeleteMapping("/api/blogs/{blogger-id}/posts/delete-all")
    public ResponseEntity<String> deleteAllPostsByBloggerId(@PathVariable("blogger-id") long bloggerId) {
        return postService.deleteAllPostsByBloggerId(bloggerId);
    }


}
