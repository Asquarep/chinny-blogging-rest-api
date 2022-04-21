package com.asquarep.bloggingrestapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    private String title;
    private String body;
    private String imageUrl;
    @OneToOne
    private Blogger postedBy;
//    @OneToMany
    private int numberOfLikes;
    private int numberOfComments;

    @OneToOne
    private Community community;

    @OneToMany
    private List<Comment> comments;
    @OneToMany
    private List<Like> likes;
    private LocalDateTime postedAt;
}
