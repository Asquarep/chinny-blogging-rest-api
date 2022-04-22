package com.asquarep.bloggingrestapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date datePosted;
    @DateTimeFormat(pattern = "HH:mm")
    private Time timePosted;
}
