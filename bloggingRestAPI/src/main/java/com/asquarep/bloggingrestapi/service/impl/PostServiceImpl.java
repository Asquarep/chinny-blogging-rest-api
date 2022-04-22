package com.asquarep.bloggingrestapi.service.impl;

import com.asquarep.bloggingrestapi.dto.CommunityDTO;
import com.asquarep.bloggingrestapi.dto.PostDTO;
import com.asquarep.bloggingrestapi.model.Community;
import com.asquarep.bloggingrestapi.model.Post;
import com.asquarep.bloggingrestapi.repository.PostRepository;
import com.asquarep.bloggingrestapi.service.CommunityService;
import com.asquarep.bloggingrestapi.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PostServiceImpl implements PostService, CommunityService {
    private final PostRepository postRepository;

    @Override
    public Community createCommunity(CommunityDTO communityDTO) {
        return null;
    }

    @Override
    public Community editCommunity(CommunityDTO communityDTO) {
        return null;
    }

    @Override
    public Post createPost(PostDTO postDTO) {
        return null;
    }

    @Override
    public List<Post> getAllPosts(PostDTO postDTO) {
        return null;
    }

    @Override
    public List<Post> getAllPostsByBloggerId(long bloggerId) {
        return null;
    }

    @Override
    public Post editPost(long postId, long bloggerId) {
        return null;
    }

    @Override
    public void deletePostById(long postId, long bloggerId) {

    }

    @Override
    public void deleteAllPostsByBloggerId(long bloggerId) {

    }
}
