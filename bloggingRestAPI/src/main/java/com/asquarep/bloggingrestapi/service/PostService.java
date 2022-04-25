package com.asquarep.bloggingrestapi.service;

import com.asquarep.bloggingrestapi.dto.BloggerDTO;
import com.asquarep.bloggingrestapi.dto.CommentDTO;
import com.asquarep.bloggingrestapi.dto.PostDTO;
import com.asquarep.bloggingrestapi.model.Blogger;
import com.asquarep.bloggingrestapi.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    Optional<PostDTO> createPost(PostDTO postDTO, long bloggerId);
    List<PostDTO> getAllPosts();
    Optional<PostDTO> getPostById(long postId);
    List<Post> getAllPostsByBloggerId(long bloggerId);
    List<BloggerDTO> getAllBlogs();
    Optional<BloggerDTO> getBlogById(long id);
    Optional<PostDTO> editPost(PostDTO postDTO, long postId, long bloggerId);
    String deletePostById(long postId, long bloggerId);
    String deleteAllPostsByBloggerId(long bloggerId);

}
