package com.asquarep.bloggingrestapi.service;

import com.asquarep.bloggingrestapi.dto.PostDTO;
import com.asquarep.bloggingrestapi.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    Optional<Post> createPost(PostDTO postDTO, long bloggerId);
    List<Post> getAllPosts();
    List<Post> getAllPostsByBloggerId(long bloggerId);
    Optional<Post> editPost(long postId, long bloggerId);
    void deletePostById(long postId, long bloggerId);
    void deleteAllPostsByBloggerId(long bloggerId);
}
