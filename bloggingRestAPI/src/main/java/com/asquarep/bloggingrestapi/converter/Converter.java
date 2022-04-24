package com.asquarep.bloggingrestapi.converter;

import com.asquarep.bloggingrestapi.dto.BloggerDTO;
import com.asquarep.bloggingrestapi.dto.CommentDTO;
import com.asquarep.bloggingrestapi.dto.CommunityDTO;
import com.asquarep.bloggingrestapi.dto.PostDTO;
import com.asquarep.bloggingrestapi.model.Blogger;
import com.asquarep.bloggingrestapi.model.Comment;
import com.asquarep.bloggingrestapi.model.Community;
import com.asquarep.bloggingrestapi.model.Post;
import com.asquarep.bloggingrestapi.repository.CommunityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@Component
public class Converter {
    private CommunityRepository communityRepository;

    public Object convertPostOrDTO(Object postOrDTO){
        Object returnObject = new Object();
        if(postOrDTO.getClass() == PostDTO.class){
            Post newPost = new Post();
            newPost.setTitle(((PostDTO) postOrDTO).getTitle());
            newPost.setBody(((PostDTO) postOrDTO).getBody());
            newPost.setImageUrl(((PostDTO) postOrDTO).getImageUrl());
            newPost.setCommunity(communityRepository.findByCommunityName(((PostDTO) postOrDTO).getCommunityName()).get());
            newPost.setDateLastUpdated(LocalDate.now());
            newPost.setLastTimePosted(LocalTime.now());
            returnObject = newPost;
        } else if(postOrDTO.getClass() == Post.class){
            PostDTO newPostDTO = new PostDTO();
            newPostDTO.setTitle(((Post) postOrDTO).getTitle());
            newPostDTO.setBody(((Post) postOrDTO).getBody());
            newPostDTO.setImageUrl(((Post) postOrDTO).getImageUrl());
            newPostDTO.setCommunityName(((Post) postOrDTO).getCommunity().getCommunityName());

            returnObject = newPostDTO;
        }
        return returnObject;
    }

    public Object convertBloggerOrDTO(Object bloggerOrDTO){
        Object returnObject = new Object();
        if(bloggerOrDTO.getClass() == BloggerDTO.class){
            Blogger newBlogger = new Blogger();
            newBlogger.setBlogId(((BloggerDTO) bloggerOrDTO).getId());
            newBlogger.setFirstName(((BloggerDTO) bloggerOrDTO).getFirstName());
            newBlogger.setLastName(((BloggerDTO) bloggerOrDTO).getLastName());
            newBlogger.setEmail(((BloggerDTO) bloggerOrDTO).getEmail());
            newBlogger.setRole(((BloggerDTO) bloggerOrDTO).getRole());

            returnObject = newBlogger;
        } else if(bloggerOrDTO.getClass() == Blogger.class){
            BloggerDTO newBloggerDTO = new BloggerDTO();
            newBloggerDTO.setId(((Blogger) bloggerOrDTO).getBlogId());
            newBloggerDTO.setFirstName(((Blogger) bloggerOrDTO).getFirstName());
            newBloggerDTO.setLastName(((Blogger) bloggerOrDTO).getLastName());
            newBloggerDTO.setRole(((Blogger) bloggerOrDTO).getRole());
            newBloggerDTO.setEmail(((Blogger) bloggerOrDTO).getEmail());

            returnObject = newBloggerDTO;
        }
        return returnObject;
    }

    public Object convertCommunityOrDTO(Object communityOrDTO){
        Object returnObject = new Object();
        if (communityOrDTO.getClass() == CommunityDTO.class) {
            Community community = new Community();
            community.setCommunityName(((CommunityDTO) communityOrDTO).getCommunityName());
            community.setCommunityDescription(((CommunityDTO) communityOrDTO).getCommunityDescription());
            community.setLastUpdatedDate(LocalDate.now());
            community.setLastUpdatedTime(LocalTime.now());
            returnObject = community;
        } else if (communityOrDTO.getClass() == Community.class){
            CommunityDTO communityDTO = new CommunityDTO();
            communityDTO.setCommunityName(((Community) communityOrDTO).getCommunityName());
            communityDTO.setCommunityDescription(((Community) communityOrDTO).getCommunityDescription());

            returnObject = communityDTO;
        }

        return returnObject;

    }

    public Object convertCommentorDTO(Object commentOrDTO){
        Object returnObject = new Object();
        if (commentOrDTO.getClass() == CommentDTO.class) {
            Comment comment = new Comment();
            comment.setCommentTitle(((CommentDTO) commentOrDTO).getCommentTitle());
            comment.setCommentBody(((CommentDTO) commentOrDTO).getCommentBody());
            comment.setLastUpdatedDate(LocalDate.now());
            comment.setLastUpdatedTime(LocalTime.now());
            returnObject = comment;
        } else if (commentOrDTO.getClass() == Comment.class){
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setCommentTitle(((Comment) commentOrDTO).getCommentTitle());
            commentDTO.setCommentBody(((Comment) commentOrDTO).getCommentBody());

            returnObject = commentDTO;
        }

        return returnObject;

    }

}
