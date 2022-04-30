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
public class Blogger extends BlogUser{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blogId;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();


    public Blogger(String firstName, String lastName, String email, String password, Long blogId, Role role, List<Post> posts) {
        super(firstName, lastName, email, password);
        this.blogId = blogId;
        this.role = role;
        this.posts = posts;
    }


}
