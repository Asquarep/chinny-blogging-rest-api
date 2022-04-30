package com.asquarep.bloggingrestapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Reader extends BlogUser{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long readerId;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy = "likes", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> likedPosts = new ArrayList<>();
    @OneToMany(mappedBy = "reader", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();


    public Reader(String firstName, String lastName, String email, String password, Long readerId, Role role, List<Post> likedPosts, List<Comment> comments) {
        super(firstName, lastName, email, password);
        this.readerId = readerId;
        this.role = role;
        this.likedPosts = likedPosts;
        this.comments = comments;
    }
}
