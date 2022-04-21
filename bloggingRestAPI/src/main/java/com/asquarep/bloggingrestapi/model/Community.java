package com.asquarep.bloggingrestapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Community {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long communityId;
    @Column(unique = true)
    private String communityTitle;
    private String communityDescription;

    @OneToOne
    private Post post;
    @OneToOne
    private Blogger createdBy;

}
