package com.asquarep.bloggingrestapi.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    private String commentTitle;
    private String commentBody;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date datePosted;
    @DateTimeFormat(pattern = "HH:mm")
    private Time timePosted;
    @ManyToOne
    private Post post;
    @ManyToOne
    private Reader reader;
}
