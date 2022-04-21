package com.asquarep.bloggingrestapi.model;

import javax.persistence.*;

@Entity
@Table(name = "likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;
    @ManyToOne
    private Post post;
    @ManyToOne
    private Reader reader;
}
