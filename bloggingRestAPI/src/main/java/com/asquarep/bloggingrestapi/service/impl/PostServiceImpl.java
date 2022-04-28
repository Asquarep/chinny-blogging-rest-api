package com.asquarep.bloggingrestapi.service.impl;

import com.asquarep.bloggingrestapi.converter.Converter;
import com.asquarep.bloggingrestapi.dto.*;
import com.asquarep.bloggingrestapi.exception.BadRequestException;
import com.asquarep.bloggingrestapi.exception.ResourceNotFoundException;
import com.asquarep.bloggingrestapi.model.*;
import com.asquarep.bloggingrestapi.repository.*;
import com.asquarep.bloggingrestapi.service.PostService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final CommunityRepository communityRepository;

    private final BloggerRepository bloggerRepository;
    private final Converter converter;
    private final ModelMapper modelMapper;


    @Override
    public ResponseEntity<CommunityDTO> createCommunity(CommunityDTO communityDTO, long bloggerId) {
        Optional<Blogger> blogger = bloggerRepository.findById(bloggerId);
        Optional<Community> communityCheck = communityRepository.findByCommunityName(communityDTO.getCommunityName());
        if (blogger.isPresent()){
            if (communityCheck.isPresent()) {
                throw new BadRequestException("Community already exists");
            } else {
                Community community = (Community) converter.convertCommunityOrDTO(communityDTO);
                community.setCreatedBy(blogger.get());
                communityRepository.save(community);
                return new ResponseEntity<>(communityDTO, HttpStatus.CREATED);
            }
        }
        throw new BadRequestException("Only registered bloggers can create communities");
    }


    @Override
    public ResponseEntity<List<CommunityDTO>> getAllCommunities() {
        List<CommunityDTO> communityDTOList = new ArrayList<>();
        var communityRepositoryAll = communityRepository.findAll();
        for (Community community : communityRepositoryAll) {
            communityDTOList.add((CommunityDTO) converter.convertCommunityOrDTO(community));
        }
        return new ResponseEntity<>(communityDTOList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CommunityDTO> getCommunityById(long communityId) {
        Optional<Community> community = communityRepository.findById(communityId);
        if (community.isPresent()) {
            CommunityDTO communityDTO = (CommunityDTO) converter.convertCommunityOrDTO(community.get());
            return new ResponseEntity<CommunityDTO>(communityDTO, HttpStatus.OK);
        }
        throw new ResourceNotFoundException("Community not found");
    }

    @Override
    public ResponseEntity<List<PostDTO>> getAllPostsByCommunity(long communityId) {
        Optional<Community> community = communityRepository.findById(communityId);
        if (community.isPresent()) {
            List<Post> listOfposts = postRepository.findAllByCommunity(community.get());
            List<PostDTO> listConverted = new ArrayList<>();
            listOfposts.forEach(post -> listConverted.add((PostDTO) converter.convertPostOrDTO(post)));
            return new ResponseEntity<List<PostDTO>>(listConverted, HttpStatus.OK);
        }
        throw new ResourceNotFoundException("Community does not exist.");
    }

    @Override
    public ResponseEntity<PostDTO> createPost(PostDTO postDTO, long bloggerId) {
        Optional<Blogger> blogger = bloggerRepository.findById(bloggerId);
        if (blogger.isPresent()){
            Optional<Community> communityCheck = communityRepository.findByCommunityName(postDTO.getCommunityName());
            Community community = new Community();
            if (communityCheck.isEmpty()) {
                community = (Community) converter.convertCommunityOrDTO(createCommunity(new CommunityDTO(postDTO.getCommunityName(), "Default Description"), bloggerId));
            }else {
                community = communityCheck.get();
            }
            Post post = (Post) converter.convertPostOrDTO(postDTO);
            post.setPostedBy(blogger.get());

            blogger.get().getPosts().add(post);
            community.getPosts().add(post);
            postRepository.save(post);
            PostDTO postDTO1 = (PostDTO) converter.convertPostOrDTO(post);
            return new ResponseEntity<PostDTO>(postDTO1 , HttpStatus.CREATED);

        }
            throw new BadRequestException("Only registered bloggers can create posts.");
    }

    @Override
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        List<PostDTO> postDTOs = new ArrayList<>();
        List<Post> allPosts = postRepository.findAll();
        for (Post post : allPosts) {
            postDTOs.add((PostDTO) converter.convertPostOrDTO(post));
        }
        return new ResponseEntity<List<PostDTO>>(postDTOs, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<PostDTO>> getAllPostsByBloggerId(long bloggerId) {
        List<PostDTO> postDTOs = new ArrayList<>();
        Optional<Blogger> blogger = bloggerRepository.findById(bloggerId);
        if(blogger.isPresent()){
            List<Post> postList = postRepository.findAllByPostedBy(blogger.get());
            for (Post post : postList) {
                postDTOs.add((PostDTO) converter.convertPostOrDTO(post));
            }
            return new ResponseEntity<List<PostDTO>>(postDTOs, HttpStatus.OK);
        }
            throw new ResourceNotFoundException("Blogger not found.");
    }

    @Override
    public ResponseEntity<List<BloggerDTO>> getAllBlogs() {
        var all = bloggerRepository.findAll();
        List<BloggerDTO> bloggerDTOList = new ArrayList<>();
        for (Blogger blogger: all) {
            bloggerDTOList.add((BloggerDTO) converter.convertBloggerOrDTO(blogger));
        }
        return new ResponseEntity<List<BloggerDTO>>(bloggerDTOList, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<BloggerDTO> getBlogById(long id) {
        Optional<Blogger> bloggerFound = bloggerRepository.findById(id);
        if (bloggerFound.isPresent()) {
            BloggerDTO bloggerDTO = (BloggerDTO) converter.convertBloggerOrDTO(bloggerFound.get());
            return new ResponseEntity<BloggerDTO>(bloggerDTO, HttpStatus.OK);
        }
        throw new ResourceNotFoundException("No blogger found with ID provided");
    }


    @Override
    public ResponseEntity<PostDTO> editPost(PostDTO postDTO, long postId, long bloggerId) {
        Optional<Blogger> bloggerCheck = bloggerRepository.findById(bloggerId);
        if (bloggerCheck.isPresent()) {
            Optional<Community> communityCheck = communityRepository.findByCommunityName(postDTO.getCommunityName());
            if (communityCheck.isPresent()) {
                Optional<Post> postCheck = postRepository.findById(postId);
                if (postCheck.isPresent() && (postCheck.get().getPostedBy().getBlogId() == bloggerId)) {
                    postCheck.get().setTitle(postDTO.getTitle());
                    postCheck.get().setBody(postDTO.getBody());
                    postCheck.get().setImageUrl(postDTO.getImageUrl());
                    postRepository.save(postCheck.get());

                    PostDTO postDTO1 = (PostDTO) converter.convertPostOrDTO(postCheck.get());
                    return new ResponseEntity<PostDTO>(postDTO1, HttpStatus.ACCEPTED);

                } else {throw new BadRequestException("Blogger can only edit posts they created.");}
            }else{throw new ResourceNotFoundException("Community does not exist. Create new community and try again.");}
        }
        throw new BadRequestException("Only registered bloggers can edit post");
    }

    @Override
    public ResponseEntity<String> deletePostById(long postId, long bloggerId) {
        Optional<Blogger> blogger = bloggerRepository.findById(bloggerId);
        if(blogger.isPresent()){
            Optional<Post> postCheck = postRepository.findByPostIdAndAndPostedBy(postId, blogger.get());
            if (postCheck.isPresent()) {
                Optional<Community> community = communityRepository.findById(postCheck.get().getCommunity().getCommunityId());

                postRepository.delete(postCheck.get());
            } else {
                throw new ResourceNotFoundException("Post not found, or blogger is not owner of post.");}
        } else {
            throw new ResourceNotFoundException("Blogger with provided id does not exist.");
        }
        return new ResponseEntity<String>("Deleted successfully.", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteAllPostsByBloggerId(long bloggerId) {
        Optional<Blogger> blogger = bloggerRepository.findById(bloggerId);
        if (blogger.isPresent()) {
            List<Post>posts = postRepository.findAllByPostedBy(blogger.get());
            for (Post post : posts) {
                deletePostById(post.getPostId(), bloggerId);
            }
            return new ResponseEntity<String>("All posts deleted Successfully", HttpStatus.OK);
        }
        throw new ResourceNotFoundException("Blogger not found.");
    }


    @Override
    public ResponseEntity<PostDTO> getPostById(long postId) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            var postDTO = (PostDTO) converter.convertPostOrDTO(post.get());
            return new ResponseEntity<PostDTO>(postDTO, HttpStatus.OK);
        }
            throw new ResourceNotFoundException("Post not found");
    }
}
