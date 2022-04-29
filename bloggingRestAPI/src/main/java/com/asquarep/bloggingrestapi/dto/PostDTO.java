package com.asquarep.bloggingrestapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostDTO {
    private Long id;
    private String title;
    private String body;
    private String imageUrl;
    private String communityName;

}
