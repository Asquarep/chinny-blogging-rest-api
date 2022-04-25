package com.asquarep.bloggingrestapi.service.impl;

import com.asquarep.bloggingrestapi.converter.Converter;
import com.asquarep.bloggingrestapi.dto.*;
import com.asquarep.bloggingrestapi.exception.BadRequestException;
import com.asquarep.bloggingrestapi.exception.ResourceNotFoundException;
import com.asquarep.bloggingrestapi.model.*;
import com.asquarep.bloggingrestapi.repository.*;
import com.asquarep.bloggingrestapi.service.CommunityService;
import com.asquarep.bloggingrestapi.service.PostService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Service
public class PostServiceImpl implements PostService, CommunityService {

    private final PostRepository postRepository;

    private final CommunityRepository communityRepository;

    private final BloggerRepository bloggerRepository;
    private final ReaderRepository readerRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;
    private final Converter converter;
    private final ModelMapper modelMapper;


    @Override
    public Optional<CommunityDTO> createCommunity(CommunityDTO communityDTO, long bloggerId) {
        Optional<Blogger> blogger = bloggerRepository.findById(bloggerId);
        Optional<Community> communityCheck = communityRepository.findByCommunityName(communityDTO.getCommunityName());
        if (communityCheck.isPresent()) {
            return Optional.empty();
        }else if (blogger.isPresent()){
            Community community = (Community) converter.convertCommunityOrDTO(communityDTO);
            community.setCreatedBy(blogger.get());
            communityRepository.save(community);
            return Optional.of(communityDTO);
        }
        return Optional.empty();
    }

    @Override
    public Optional<CommunityDTO> editCommunity(CommunityDTO communityDTO) {
        return null;
    }

    @Override
    public List<CommunityDTO> getAllCommunities() {
        List<CommunityDTO> communityDTOList = new ArrayList<>();
        var communityRepositoryAll = communityRepository.findAll();
        for (Community community : communityRepositoryAll) {
            communityDTOList.add((CommunityDTO) converter.convertCommunityOrDTO(community));
        }
        return communityDTOList;
    }

    @Override
    public Optional<CommunityDTO> getCommunityById(long communityId) {
        Optional<Community> community = communityRepository.findById(communityId);
        if (community.isPresent()) {
            CommunityDTO communityDTO = (CommunityDTO) converter.convertCommunityOrDTO(community.get());
            return Optional.of(communityDTO);
        }
        return Optional.empty();
    }

    @Override
    public Optional<PostDTO> createPost(PostDTO postDTO, long bloggerId) {
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
            return Optional.of(postDTO1);

        }
        return Optional.empty();
    }

    @Override
    public List<PostDTO> getAllPosts() {
        List<PostDTO> postDTOs = new ArrayList<>();
        List<Post> allPosts = postRepository.findAll();
        for (Post post : allPosts) {
            postDTOs.add((PostDTO) converter.convertPostOrDTO(post));
        }
        return postDTOs;
    }

    @Override
    public List<Post> getAllPostsByBloggerId(long bloggerId) {
        Optional<Blogger> blogger = bloggerRepository.findById(bloggerId);
        if(blogger.isEmpty()){
            throw new ResourceNotFoundException("Blogger not found.");
        }
            return postRepository.findAllByPostedBy(blogger.get());
    }

    @Override
    public List<BloggerDTO> getAllBlogs() {
        var all = bloggerRepository.findAll();
        List<BloggerDTO> bloggerDTOList = new ArrayList<>();
        for (Blogger blogger: all) {
            bloggerDTOList.add((BloggerDTO) converter.convertBloggerOrDTO(blogger));
        }
        return bloggerDTOList;
    }


    @Override
    public Optional<BloggerDTO> getBlogById(long id) {
        Optional<Blogger> bloggerFound = bloggerRepository.findById(id);
        if (bloggerFound.isPresent()) {
            BloggerDTO bloggerDTO = (BloggerDTO) converter.convertBloggerOrDTO(bloggerFound.get());
            return Optional.of(bloggerDTO);
        }
        return Optional.empty();
    }


    @Override
    public Optional<PostDTO> editPost(PostDTO postDTO, long postId, long bloggerId) {
        Optional<Blogger> bloggerCheck = bloggerRepository.findById(bloggerId);
        if (bloggerCheck.isPresent()) {
            Optional<Community> communityCheck = communityRepository.findByCommunityName(postDTO.getCommunityName());
            if (communityCheck.isPresent()) {
                Optional<Post> postCheck = postRepository.findById(postId);
                if (postCheck.isPresent() && (postCheck.get().getPostedBy().getBlogId() == bloggerId)) {
                    postCheck.get().setTitle(postDTO.getTitle());
                    postCheck.get().setBody(postDTO.getBody());
                    postCheck.get().setImageUrl(postDTO.getImageUrl());
//                    editedPost.setTitle(postDTO.getTitle());
//                    editedPost.setBody(postDTO.getBody());
//                    editedPost.setImageUrl(postDTO.getImageUrl());
//                    editedPost.setCommunity(communityCheck.get());
                    postRepository.save(postCheck.get());

                    PostDTO postDTO1 = (PostDTO) converter.convertPostOrDTO(postCheck.get());
                    return Optional.of(postDTO1);

                } else {throw new BadRequestException("Blogger can only edit posts they created.");}
            }else{throw new ResourceNotFoundException("Community does not exist. Create new community and try again.");}
        }
        return Optional.empty();
    }

    @Override
    public String deletePostById(long postId, long bloggerId) {
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
        return "Deleted successfully.";
    }

    @Override
    public String deleteAllPostsByBloggerId(long bloggerId) {
        Optional<Blogger> blogger = bloggerRepository.findById(bloggerId);
        if (blogger.isPresent()) {
            List<Post>posts = postRepository.findAllByPostedBy(blogger.get());
            for (Post post : posts) {
                deletePostById(post.getPostId(), bloggerId);
            }
            return "All posts deleted Successfully";
        }
        return null;
    }


    @Override
    public Optional<PostDTO> getPostById(long postId) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isEmpty()) {
            throw new ResourceNotFoundException("Post not found");
        }
        var postDTO = (PostDTO) converter.convertPostOrDTO(post.get());
        return Optional.of(postDTO);
    }
}
