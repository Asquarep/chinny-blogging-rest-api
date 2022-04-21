package com.asquarep.bloggingrestapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Blogger extends BlogUser{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blogId;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany
    private List<Post> blogPosts;


    public Blogger(String firstName, String lastName, String email, String password, Long blogId, Role role, List<Post> blogPosts) {
        super(firstName, lastName, email, password);
        this.blogId = blogId;
        this.role = role;
        this.blogPosts = blogPosts;
    }

}
