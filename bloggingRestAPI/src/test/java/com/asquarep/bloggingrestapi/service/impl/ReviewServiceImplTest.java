package com.asquarep.bloggingrestapi.service.impl;

import com.asquarep.bloggingrestapi.converter.Converter;
import com.asquarep.bloggingrestapi.dto.BloggerDTO;
import com.asquarep.bloggingrestapi.dto.CommunityDTO;
import com.asquarep.bloggingrestapi.dto.PostDTO;
import com.asquarep.bloggingrestapi.model.*;
import com.asquarep.bloggingrestapi.repository.BloggerRepository;
import com.asquarep.bloggingrestapi.repository.CommunityRepository;
import com.asquarep.bloggingrestapi.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ReviewServiceImplTest {
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
    private Post post3;
    private PostDTO postDTO;
    private PostDTO postDTO2;
    private PostDTO postDTO3;
    private Blogger blogger;
    private Blogger blogger2;
    private Reader reader;
    private Reader reader2;
    private BloggerDTO bloggerDTO;
    private BloggerDTO bloggerDTO2;


    @BeforeEach
    void setUp() {
        community = Community.builder().communityId(1L).communityName("Beyonce").communityDescription("Beyonce").createdBy(blogger).posts(new ArrayList<>()).build();
        community2 = Community.builder().communityId(2L).communityName("Kim").communityDescription("Kim").createdBy(blogger).build();
        communityDTO = CommunityDTO.builder().communityName("Beyonce").communityDescription("Beyonce").build();
        communityDTO2 = CommunityDTO.builder().communityName("Kim").communityDescription("Kim").build();

        blogger = new Blogger();
        blogger.setBlogId(1L);
        blogger.setFirstName("Abisayo");
        blogger.setLastName("Abiodun");
        blogger.setEmail("abisayo@gmail.com");
        blogger.setPassword("12345");
        blogger.setRole(Role.BLOGGER);
        blogger2 = new Blogger();
        blogger2.setBlogId(2L);
        blogger2.setFirstName("Peter");
        blogger2.setLastName("Abbey");
        blogger2.setEmail("peter@gmail.com");
        blogger2.setPassword("12345");
        blogger2.setRole(Role.BLOGGER);
        bloggerDTO = new BloggerDTO();
        bloggerDTO.setId(1L);
        bloggerDTO.setFirstName("Abisayo");
        bloggerDTO.setLastName("Abiodun");
        bloggerDTO.setEmail("abisayo@gmail.com");
        bloggerDTO.setRole(Role.BLOGGER);
        bloggerDTO2 = new BloggerDTO();
        bloggerDTO2.setId(2L);
        bloggerDTO2.setFirstName("Peter");
        bloggerDTO2.setLastName("Abbey");
        bloggerDTO2.setEmail("peter@gmail.com");
        bloggerDTO2.setRole(Role.BLOGGER);

        post = Post.builder().postId(1L).title("Beyonce is pregnant").body("And fans don't think its Jay-z's").imageUrl("newimage/png").community(community).postedBy(blogger).build();
        post3 = Post.builder().postId(1L).title("Beyonce is pregnant").body("And fans don't think its Jay-z's").imageUrl("newimage/png").community(community).postedBy(blogger).build();
        post2 = Post.builder().postId(2L).title("Kim Kardashian's latest look").body("And she is opening a new business in Los Angeles").imageUrl("newimage2/png").community(community2).postedBy(blogger2).build();
        postDTO = PostDTO.builder().id(1L).title("Beyonce is pregnant").body("And fans don't think its Jay-z's").imageUrl("newimage2/png").communityName(community.getCommunityName()).build();
        postDTO3 = PostDTO.builder().id(1L).title("Beyonce is pregnant").body("And fans don't think its Jay-z's").imageUrl("newimage2/png").communityName(community.getCommunityName()).build();
        postDTO2 = PostDTO.builder().id(2L).title("Kim Kardashian's latest look").body("And she is opening a new business in Los Angeles").imageUrl("newimage2/png").communityName(community2.getCommunityName()).build();

        reader = new Reader();
        reader.setReaderId(1L);
        reader.setFirstName("Sinebi");
        reader.setLastName("Innazo");
        reader.setEmail("sinebi@gmail.com");
        reader.setPassword("12345");
        reader.setRole(Role.READER);
        reader2 = new Reader();
        reader2.setReaderId(2L);
        reader2.setFirstName("Dennis");
        reader2.setLastName("Okoye");
        reader2.setEmail("dennis@gmail.com");
        reader2.setPassword("12345");
        reader2.setRole(Role.READER);

    }

    @Test
    void likeOrUnlikePost() {
    }

    @Test
    void unlikePost() {
    }

    @Test
    void commentOnPost() {
    }

    @Test
    void testCommentOnPost() {
    }

    @Test
    void readerDeleteComment() {
    }

    @Test
    void bloggerDeleteComment() {
    }
}