package com.asquarep.bloggingrestapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentDTO {
    private String commentTitle;
    private String commentBody;
}
