package com.asquarep.bloggingrestapi.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    private String commentTitle;
    private String commentBody;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastUpdatedDate;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime lastUpdatedTime;

    @ManyToOne
    private Post post;

    @ManyToOne
    private Reader reader;
}
