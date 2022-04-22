package com.asquarep.bloggingrestapi.service.impl;

import com.asquarep.bloggingrestapi.dto.CommunityDTO;
import com.asquarep.bloggingrestapi.dto.PostDTO;
import com.asquarep.bloggingrestapi.exception.BadRequestException;
import com.asquarep.bloggingrestapi.exception.ResourceNotFoundException;
import com.asquarep.bloggingrestapi.model.Blogger;
import com.asquarep.bloggingrestapi.model.Community;
import com.asquarep.bloggingrestapi.model.Post;
import com.asquarep.bloggingrestapi.repository.BloggerRepository;
import com.asquarep.bloggingrestapi.repository.CommunityRepository;
import com.asquarep.bloggingrestapi.repository.PostRepository;
import com.asquarep.bloggingrestapi.service.CommunityService;
import com.asquarep.bloggingrestapi.service.PostService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Service
public class PostServiceImpl implements PostService, CommunityService {

    private final PostRepository postRepository;

    private final CommunityRepository communityRepository;

    private final BloggerRepository bloggerRepository;

//    @Autowired
//    public PostServiceImpl(PostRepository postRepository, CommunityRepository communityRepository, BloggerRepository bloggerRepository) {
//        this.postRepository = postRepository;
//        this.communityRepository = communityRepository;
//        this.bloggerRepository = bloggerRepository;
//    }

    @Override
    public Community createCommunity(CommunityDTO communityDTO, long bloggerId) {
        Optional<Blogger> blogger = bloggerRepository.findById(bloggerId);
        Optional<Community> communityCheck = communityRepository.findByCommunityName(communityDTO.getCommunityName());
        if (communityCheck.isPresent()) {
            return null;
        }else if (blogger.isPresent()){
            Community community = new Community();
            community.setCommunityName(communityDTO.getCommunityName());
            community.setCommunityDescription(communityDTO.getCommunityDescription());
            community.setCreatedBy(blogger.get());
            community.setDateCreated(LocalDate.now());
            community.setTimeCreated(LocalTime.now());

            communityRepository.save(community);
            return community;
        }
        return null;
    }

    @Override
    public Community editCommunity(CommunityDTO communityDTO) {
        return null;
    }

    @Override
    public List<Community> getAllComunities() {
        return null;
    }

    @Override
    public Optional<Post> createPost(PostDTO postDTO, long bloggerId) {
        Optional<Blogger> blogger = bloggerRepository.findById(bloggerId);
        if (blogger.isPresent()){
            Optional<Community> communityCheck = communityRepository.findByCommunityName(postDTO.getCommunityName());
            Community community = new Community();
            if (communityCheck.isEmpty()) {
                community = createCommunity(new CommunityDTO(postDTO.getCommunityName(), "Default Description"), bloggerId);
            }else {
                community = communityCheck.get();
            }
            Post post = new Post();
            post.setTitle(postDTO.getTitle());
            post.setBody(postDTO.getBody());
            post.setImageUrl(postDTO.getImageUrl());
            post.setCommunity(community);
            post.setPostedBy(blogger.get());
            post.setDatePosted(LocalDate.now());
            post.setTimePosted(LocalTime.now());

            blogger.get().getPosts().add(post);
            community.getPosts().add(post);
            postRepository.save(post);
            return Optional.of(post);

        }
        return Optional.empty();
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> getAllPostsByBloggerId(long bloggerId) {
        return null;
    }

    @Override
    public Optional<Post> editPost(long postId, long bloggerId) {
        return Optional.empty();
    }

    @Override
    public void deletePostById(long postId, long bloggerId) {

    }

    @Override
    public void deleteAllPostsByBloggerId(long bloggerId) {

    }
}
