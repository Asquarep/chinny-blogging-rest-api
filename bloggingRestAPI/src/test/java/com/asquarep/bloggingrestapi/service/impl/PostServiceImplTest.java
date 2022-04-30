package com.asquarep.bloggingrestapi.service.impl;

import com.asquarep.bloggingrestapi.converter.Converter;
import com.asquarep.bloggingrestapi.dto.BloggerDTO;
import com.asquarep.bloggingrestapi.dto.CommunityDTO;
import com.asquarep.bloggingrestapi.dto.PostDTO;
import com.asquarep.bloggingrestapi.exception.BadRequestException;
import com.asquarep.bloggingrestapi.exception.ResourceNotFoundException;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
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

        blogger = new Blogger();blogger.setBlogId(1L);blogger.setFirstName("Abisayo");blogger.setLastName("Abiodun");blogger.setEmail("abisayo@gmail.com");blogger.setPassword("12345");blogger.setRole(Role.BLOGGER);
        blogger2 = new Blogger();blogger2.setBlogId(2L);blogger2.setFirstName("Peter");blogger2.setLastName("Abbey");blogger2.setEmail("peter@gmail.com");blogger2.setPassword("12345");blogger2.setRole(Role.BLOGGER);
        bloggerDTO = new BloggerDTO();bloggerDTO.setId(1L);bloggerDTO.setFirstName("Abisayo");bloggerDTO.setLastName("Abiodun");bloggerDTO.setEmail("abisayo@gmail.com");bloggerDTO.setRole(Role.BLOGGER);
        bloggerDTO2 = new BloggerDTO();bloggerDTO2.setId(2L);bloggerDTO2.setFirstName("Peter");bloggerDTO2.setLastName("Abbey");bloggerDTO2.setEmail("peter@gmail.com");bloggerDTO2.setRole(Role.BLOGGER);

        post = Post.builder().postId(1L).title("Beyonce is pregnant").body("And fans don't think its Jay-z's").imageUrl("newimage/png").community(community).postedBy(blogger).build();
        post3 = Post.builder().postId(1L).title("Beyonce is pregnant").body("And fans don't think its Jay-z's").imageUrl("newimage/png").community(community).postedBy(blogger).build();
        post2 = Post.builder().postId(2L).title("Kim Kardashian's latest look").body("And she is opening a new business in Los Angeles").imageUrl("newimage2/png").community(community2).postedBy(blogger2).build();
        postDTO = PostDTO.builder().id(1L).title("Beyonce is pregnant").body("And fans don't think its Jay-z's").imageUrl("newimage2/png").communityName(community.getCommunityName()).build();
        postDTO3 = PostDTO.builder().id(1L).title("Beyonce is pregnant").body("And fans don't think its Jay-z's").imageUrl("newimage2/png").communityName(community.getCommunityName()).build();
        postDTO2 = PostDTO.builder().id(2L).title("Kim Kardashian's latest look").body("And she is opening a new business in Los Angeles").imageUrl("newimage2/png").communityName(community2.getCommunityName()).build();

        reader = new Reader();reader.setReaderId(1L);reader.setFirstName("Sinebi");reader.setLastName("Innazo");reader.setEmail("sinebi@gmail.com");reader.setPassword("12345");reader.setRole(Role.READER);
        reader2 = new Reader();reader2.setReaderId(2L);reader2.setFirstName("Dennis");reader2.setLastName("Okoye");reader2.setEmail("dennis@gmail.com");reader2.setPassword("12345");reader2.setRole(Role.READER);


//        Mockito.when(bloggerRepository.findById(1L)).thenReturn(Optional.of(blogger));
//        Mockito.when(bloggerRepository.findById(2L)).thenReturn(Optional.of(blogger2));
//
//        Mockito.when(postRepository.findById(1L)).thenReturn(Optional.of(post));
//        Mockito.when(postRepository.findById(2L)).thenReturn(Optional.of(post2));
//
//        Mockito.when(communityRepository.findById(1L)).thenReturn(Optional.of(community));
//        Mockito.when(communityRepository.findById(2L)).thenReturn(Optional.of(community2));
//        Mockito.when(communityRepository.save(community)).thenReturn(community);
//        Mockito.when(communityRepository.save(community2)).thenReturn(community2);
//        Mockito.when(communityRepository.findByCommunityName("Beyonce")).thenReturn(Optional.of(community));
//        Mockito.when(communityRepository.findByCommunityName("Kim")).thenReturn(Optional.of(community2));
//        Mockito.when(converter.convertCommunityOrDTO(communityDTO)).thenReturn(community);
//        Mockito.when(converter.convertCommunityOrDTO(communityDTO2)).thenReturn(community2);
//        Mockito.when(converter.convertCommunityOrDTO(community)).thenReturn(communityDTO);
//        Mockito.when(converter.convertCommunityOrDTO(community2)).thenReturn(communityDTO2);

//        Mockito.when(converter.convertPostOrDTO(post)).thenReturn(postDTO);
//        Mockito.when(converter.convertPostOrDTO(post2)).thenReturn(postDTO2);
//        Mockito.when(converter.convertPostOrDTO(postDTO)).thenReturn(post);
//        Mockito.when(converter.convertPostOrDTO(postDTO2)).thenReturn(post2);



    }

    @Test
    void createCommunity() {
        Mockito.when(bloggerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(BadRequestException.class, () -> postService.createCommunity(communityDTO, blogger.getBlogId()), "Only registered bloggers can create communities");

        Mockito.when(bloggerRepository.findById(1L)).thenReturn(Optional.of(blogger));
        Mockito.when(communityRepository.findByCommunityName(any())).thenReturn(Optional.of(community));

        assertThrows(BadRequestException.class, () -> postService.createCommunity(communityDTO, blogger.getBlogId()), "Community already exists.");

        Mockito.when(bloggerRepository.findById(1L)).thenReturn(Optional.of(blogger));
        Mockito.when(communityRepository.findByCommunityName(any())).thenReturn(Optional.empty());
        Mockito.when(converter.convertCommunityOrDTO(communityDTO)).thenReturn(community);
        Mockito.when(communityRepository.save(community)).thenReturn(community);
        ResponseEntity<CommunityDTO> responseEntity = postService.createCommunity(communityDTO, blogger.getBlogId());

        Assertions.assertThat(responseEntity.getBody()).isEqualTo(communityDTO);

    }

    @Test
    void getAllCommunities() {
        List<Community> communityList = List.of(community, community2);
        Mockito.when(communityRepository.findAll()).thenReturn(communityList);
        List<CommunityDTO> communityDTOList = List.of(communityDTO, communityDTO2);
        Mockito.when(converter.convertCommunityOrDTO(community)).thenReturn(communityDTO);
        Mockito.when(converter.convertCommunityOrDTO(community2)).thenReturn(communityDTO2);
        ResponseEntity<List<CommunityDTO>> responseEntity = postService.getAllCommunities();
        Assertions.assertThat(responseEntity.getBody()).isEqualTo(communityDTOList);
    }

    @Test
    void getCommunityById() {
        Mockito.when(communityRepository.findById(1L)).thenReturn(Optional.of(community));
        Mockito.when(converter.convertCommunityOrDTO(any())).thenReturn(communityDTO);
        ResponseEntity<CommunityDTO> responseEntity = postService.getCommunityById(1L);
        Assertions.assertThat(responseEntity.getBody()).isEqualTo(communityDTO);
        Mockito.when(communityRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> postService.getCommunityById(1L), "Community not found");

    }

    @Test
    void getAllPostsByCommunity() {
        Mockito.when(communityRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, ()-> postService.getCommunityById(1L), "Community does not exist.");

        List<Post> postList = List.of(post);
        List<PostDTO> postDTOS = List.of(postDTO);

        Mockito.when(communityRepository.findById(1L)).thenReturn(Optional.of(community));
        Mockito.when(postRepository.findAllByCommunity(any())).thenReturn(postList);
        Mockito.when(converter.convertPostOrDTO(any())).thenReturn(postDTO);
        ResponseEntity<List<PostDTO>> responseEntity = postService.getAllPostsByCommunity(1L);
        Assertions.assertThat(responseEntity.getBody()).isEqualTo(postDTOS);

    }

    @Test
    void createPost() {
        Mockito.when(bloggerRepository.findById(1L)).thenReturn(Optional.of(blogger));
        Mockito.when(communityRepository.findByCommunityName(any())).thenReturn(Optional.of(community));
        Mockito.when(communityRepository.save(any())).thenReturn(community);
        Mockito.when(converter.convertPostOrDTO(postDTO)).thenReturn(post);
        Mockito.when(converter.convertPostOrDTO(post)).thenReturn(postDTO);
        Mockito.when(postRepository.save(post)).thenReturn(post);
        ResponseEntity<PostDTO> responseEntity = postService.createPost(postDTO, 1L);
        Assertions.assertThat(responseEntity.getBody()).isEqualTo(postDTO);

        Mockito.when(communityRepository.findByCommunityName(any())).thenReturn(Optional.empty());
        Mockito.when(converter.convertCommunityOrDTO(any())).thenReturn(community);
        ResponseEntity<PostDTO> responseEntity2 = postService.createPost(postDTO, 1L);
        Assertions.assertThat(responseEntity2.getBody()).isEqualTo(postDTO);



        Mockito.when(bloggerRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(BadRequestException.class, ()-> postService.createPost(postDTO, 1L), "Only registered bloggers can create posts.");

    }

    @Test
    void getAllPosts() {
        List<PostDTO> postDTOS = List.of(postDTO, postDTO2);
        List<Post> postList = List.of(post, post2);
        Mockito.when(converter.convertPostOrDTO(post)).thenReturn(postDTO);
        Mockito.when(converter.convertPostOrDTO(post2)).thenReturn(postDTO2);
        Mockito.when(postRepository.findAll()).thenReturn(postList);
        ResponseEntity<List<PostDTO>> responseEntity = postService.getAllPosts();
        Assertions.assertThat(responseEntity.getBody()).isEqualTo(postDTOS);

    }

    @Test
    void getAllPostsByBloggerId() {
        Mockito.when(bloggerRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, ()-> postService.getAllPostsByBloggerId(1L), "Blogger not found.");

        List<Post> postList = List.of(post, post3);
        List<PostDTO> postDTOS = List.of(postDTO, postDTO3);
        Mockito.when(bloggerRepository.findById(1L)).thenReturn(Optional.of(blogger));
        Mockito.when(postRepository.findAllByPostedBy(blogger)).thenReturn(postList);
        Mockito.when(converter.convertPostOrDTO(post)).thenReturn(postDTO);
        Mockito.when(converter.convertPostOrDTO(post3)).thenReturn(postDTO3);

        ResponseEntity<List<PostDTO>> responseEntity = postService.getAllPostsByBloggerId(1L);
        Assertions.assertThat(responseEntity.getBody()).isEqualTo(postDTOS);

    }


    @Test
    void getAllBlogs() {
        List<Blogger> bloggerList = List.of(blogger, blogger2);
        List<BloggerDTO> bloggerDTOList = List.of(bloggerDTO, bloggerDTO2);
        Mockito.when(bloggerRepository.findAll()).thenReturn(bloggerList);
        Mockito.when(converter.convertBloggerOrDTO(blogger)).thenReturn(bloggerDTO);
        Mockito.when(converter.convertBloggerOrDTO(blogger2)).thenReturn(bloggerDTO2);
        ResponseEntity<List<BloggerDTO>> responseEntity = postService.getAllBlogs();
        Assertions.assertThat(responseEntity.getBody()).isEqualTo(bloggerDTOList);

    }

    @Test
    void getBlogById() {
        Mockito.when(bloggerRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, ()-> postService.getBlogById(1L), "No blogger found with ID provided.");
        Mockito.when(bloggerRepository.findById(1L)).thenReturn(Optional.of(blogger));
        Mockito.when(converter.convertBloggerOrDTO(blogger)).thenReturn(bloggerDTO);
        ResponseEntity<BloggerDTO> responseEntity = postService.getBlogById(1L);
        Assertions.assertThat(responseEntity.getBody()).isEqualTo(bloggerDTO);

    }

    @Test
    void editPost() {
        Mockito.when(bloggerRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(BadRequestException.class, ()-> postService.editPost(postDTO, 1L, 1L), "Only registered bloggers can edit post");
        Mockito.when(bloggerRepository.findById(1L)).thenReturn(Optional.of(blogger));
        Mockito.when(communityRepository.findByCommunityName(any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, ()-> postService.editPost(postDTO, 1L, 1L), "Community does not exist. Create new community and try again.");
        Mockito.when(bloggerRepository.findById(1L)).thenReturn(Optional.of(blogger));
        Mockito.when(communityRepository.findByCommunityName(any())).thenReturn(Optional.of(community));
        Mockito.when(postRepository.findById(any())).thenReturn(Optional.of(post));
        Mockito.when(postRepository.save(post)).thenReturn(post);
        Mockito.when(converter.convertPostOrDTO(post)).thenReturn(postDTO);
        ResponseEntity<PostDTO> responseEntity = postService.editPost(postDTO, 1,1);
        Assertions.assertThat(responseEntity.getBody()).isEqualTo(postDTO);
        assertThrows(BadRequestException.class, ()-> postService.editPost(postDTO, 1L, 2L), "Blogger can only edit posts they created.");

    }

    @Test
    void deletePostById() {
//
//
//
//        Mockito.when(bloggerRepository.findById(any())).thenReturn(Optional.of(blogger));
//        Mockito.when(postRepository.findByPostIdAndAndPostedBy(1L, blogger)).thenReturn(Optional.of(post));
//        Mockito.when(postRepository.delete(post)).thenReturn((int) 1);
//
//
//
//        Optional<Blogger> blogger = bloggerRepository.findById(bloggerId);
//        if(blogger.isPresent()){
//            Optional<Post> postCheck = postRepository.findByPostIdAndAndPostedBy(postId, blogger.get());
//            if (postCheck.isPresent()) {
//                Optional<Community> community = communityRepository.findById(postCheck.get().getCommunity().getCommunityId());
//
//                postRepository.delete(postCheck.get());
//            } else {
//                throw new ResourceNotFoundException("Post not found, or blogger is not owner of post.");}
//        } else {
//            throw new ResourceNotFoundException("Blogger with provided id does not exist.");
//        }
//        return new ResponseEntity<String>("Deleted successfully.", HttpStatus.OK);

    }

    @Test
    void deleteAllPostsByBloggerId() {
    }

    @Test
    void getPostById() {
    }
}