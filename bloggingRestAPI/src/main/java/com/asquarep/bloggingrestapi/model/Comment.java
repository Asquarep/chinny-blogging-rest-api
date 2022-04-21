package com.asquarep.bloggingrestapi.model;

import javax.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    private String commentBody;
    @ManyToOne
    private Post post;
    @ManyToOne
    private Reader reader;
}
