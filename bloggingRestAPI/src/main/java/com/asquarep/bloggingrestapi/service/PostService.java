package com.asquarep.bloggingrestapi.service;

import com.asquarep.bloggingrestapi.dto.PostDTO;
import com.asquarep.bloggingrestapi.model.Post;

import java.util.List;

public interface PostService {
    Post createPost(PostDTO postDTO);
    List<Post> getAllPosts(PostDTO postDTO);
    List<Post> getAllPostsByBloggerId(long bloggerId);
    Post editPost(long postId, long bloggerId);
    void deletePostById(long postId, long bloggerId);
    void deleteAllPostsByBloggerId(long bloggerId);
}
