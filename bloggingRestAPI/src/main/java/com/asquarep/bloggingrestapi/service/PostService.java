package com.asquarep.bloggingrestapi.service;

import com.asquarep.bloggingrestapi.dto.BloggerDTO;
import com.asquarep.bloggingrestapi.dto.PostDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService extends CommunityService{
    ResponseEntity<PostDTO> createPost(PostDTO postDTO, long bloggerId);
    ResponseEntity<List<PostDTO>> getAllPosts();
    ResponseEntity<PostDTO> getPostById(long postId);
    ResponseEntity<List<PostDTO>> getAllPostsByBloggerId(long bloggerId);
    ResponseEntity<List<BloggerDTO>> getAllBlogs();
    ResponseEntity<BloggerDTO> getBlogById(long id);
    ResponseEntity<PostDTO> editPost(PostDTO postDTO, long postId, long bloggerId);
    ResponseEntity<String> deletePostById(long postId, long bloggerId);
    ResponseEntity<String> deleteAllPostsByBloggerId(long bloggerId);

}
