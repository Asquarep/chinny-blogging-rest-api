package com.asquarep.bloggingrestapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class CommentDTO {
    private String commentTitle;
    private String commentBody;
    private long postId;
    private long readerId;
}
