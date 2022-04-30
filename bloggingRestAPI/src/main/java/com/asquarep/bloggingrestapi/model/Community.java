package com.asquarep.bloggingrestapi.model;

import lombok.*;
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
@Builder
@Entity
public class Community {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long communityId;
    @Column(unique = true)
    private String communityName;
    private String communityDescription;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastUpdatedDate;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime lastUpdatedTime;

//    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "community", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();
    @OneToOne
    private Blogger createdBy;

}
