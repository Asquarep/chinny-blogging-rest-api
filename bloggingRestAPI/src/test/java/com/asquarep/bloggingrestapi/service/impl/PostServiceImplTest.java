package com.asquarep.bloggingrestapi.service.impl;

import com.asquarep.bloggingrestapi.converter.Converter;
import com.asquarep.bloggingrestapi.dto.BloggerDTO;
import com.asquarep.bloggingrestapi.dto.CommunityDTO;
import com.asquarep.bloggingrestapi.dto.PostDTO;
import com.asquarep.bloggingrestapi.exception.BadRequestException;
import com.asquarep.bloggingrestapi.model.*;
import com.asquarep.bloggingrestapi.repository.BloggerRepository;
import com.asquarep.bloggingrestapi.repository.CommunityRepository;
import com.asquarep.bloggingrestapi.repository.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {

    @InjectMocks
    private PostServiceImpl postService;

    @Mock
    private PostRepository postRepository;
    @Mock
    private BloggerRepository bloggerRepository;
    @Mock
    private CommunityRepository communityRepository;
    @Mock
    private Converter converter;

    private Community community;
    private Community community2;
    private CommunityDTO communityDTO;
    private CommunityDTO communityDTO2;
    private Post post;
    private Post post2;
    private PostDTO postDTO;
    private PostDTO postDTO2;
    private Blogger blogger;
    private Blogger blogger2;
    private Reader reader;
    private Reader reader2;
    private BloggerDTO bloggerDTO;
    private BloggerDTO bloggerDTO2;


    @BeforeEach
    void setUp() {
        post = Post.builder().postId(1l).title("Beyonce is pregnant").body("And fans don't think its Jay-z's").imageUrl("newimage/png").community(community).postedBy(blogger).build();
        post2 = Post.builder().postId(2l).title("Kim Kardashian's latest look").body("And she is opening a new business in Los Angeles").imageUrl("newimage2/png").community(community2).postedBy(blogger2).build();
        postDTO = PostDTO.builder().id(1l).title("Beyonce is pregnant").body("And fans don't think its Jay-z's").imageUrl("newimage2/png").communityName(community.getCommunityName()).build();
        postDTO2 = PostDTO.builder().id(2l).title("Kim Kardashian's latest look").body("And she is opening a new business in Los Angeles").imageUrl("newimage2/png").communityName(community2.getCommunityName()).build();

        blogger = new Blogger();blogger.setBlogId(1L);blogger.setFirstName("Abisayo");blogger.setLastName("Abiodun");blogger.setEmail("abisayo@gmail.com");blogger.setPassword("12345");blogger.setRole(Role.BLOGGER);
        blogger2 = new Blogger();blogger2.setBlogId(2L);blogger2.setFirstName("Peter");blogger2.setLastName("Abbey");blogger2.setEmail("peter@gmail.com");blogger2.setPassword("12345");blogger2.setRole(Role.BLOGGER);

        reader = new Reader();reader.setReaderId(1L);reader.setFirstName("Sinebi");reader.setLastName("Innazo");reader.setEmail("sinebi@gmail.com");reader.setPassword("12345");reader.setRole(Role.READER);
        reader = new Reader();reader2.setReaderId(2L);reader2.setFirstName("Dennis");reader2.setLastName("Okoye");reader2.setEmail("dennis@gmail.com");reader2.setPassword("12345");reader2.setRole(Role.READER);

        community = Community.builder().communityId(1L).communityName("Beyonce").communityDescription("Beyonce").createdBy(blogger).build();
        community2 = Community.builder().communityId(2L).communityName("Kim").communityDescription("Kim").createdBy(blogger).build();



        Mockito.when(bloggerRepository.findById(1L)).thenReturn(Optional.of(blogger));
        Mockito.when(bloggerRepository.findById(2L)).thenReturn(Optional.of(blogger2));

        Mockito.when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        Mockito.when(postRepository.findById(2L)).thenReturn(Optional.of(post2));

        Mockito.when(communityRepository.findById(1L)).thenReturn(Optional.of(community));
        Mockito.when(communityRepository.findById(2L)).thenReturn(Optional.of(community2));
        Mockito.when(communityRepository.findByCommunityName("Beyonce")).thenReturn(Optional.of(community));
        Mockito.when(communityRepository.findByCommunityName("Kim")).thenReturn(Optional.of(community2));

        Mockito.when(converter.convertPostOrDTO(post)).thenReturn(postDTO);
        Mockito.when(converter.convertPostOrDTO(post2)).thenReturn(postDTO2);
        Mockito.when(converter.convertPostOrDTO(postDTO)).thenReturn(post);
        Mockito.when(converter.convertPostOrDTO(postDTO2)).thenReturn(post2);


        Mockito.when(converter.convertCommunityOrDTO(communityDTO)).thenReturn(community);
    }

    @Test
    void createCommunity() {
////        Mockito.when(converter.convertBloggerOrDTO(bloggerDTO)).thenReturn(blogger);
//        Mockito.when(bloggerRepository.save(any())).thenReturn(blogger);
//        ResponseEntity<String> responseEntity = bloggerService.bloggerSignUp(signUpDTO);
//        Assertions.assertThat(responseEntity.getBody()).isEqualTo("New Blog created successfully.");
//        Mockito.when(bloggerRepository.findBloggerByEmail(signUpDTO.getEmail())).thenReturn(Optional.of(blogger));
//        assertThrows(BadRequestException.class, () -> bloggerService.bloggerSignUp(signUpDTO), "Blogger with this email already exists.");
    }

    @Test
    void getAllCommunities() {
    }

    @Test
    void getCommunityById() {
    }

    @Test
    void getAllPostsByCommunity() {
    }

    @Test
    void createPost() {
    }

    @Test
    void getAllPosts() {
    }

    @Test
    void getAllPostsByBloggerId() {
    }

    @Test
    void getAllBlogs() {
    }

    @Test
    void getBlogById() {
    }

    @Test
    void editPost() {
    }

    @Test
    void deletePostById() {
    }

    @Test
    void deleteAllPostsByBloggerId() {
    }

    @Test
    void getPostById() {
    }
}