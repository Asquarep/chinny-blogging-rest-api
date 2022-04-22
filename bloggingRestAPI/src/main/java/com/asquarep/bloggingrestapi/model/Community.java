package com.asquarep.bloggingrestapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
    private String communityName;
    private String communityDescription;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateCreated;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime timeCreated;

    @OneToMany
    private List<Post> posts = new ArrayList<>();
    @OneToOne
    private Blogger createdBy;

}
