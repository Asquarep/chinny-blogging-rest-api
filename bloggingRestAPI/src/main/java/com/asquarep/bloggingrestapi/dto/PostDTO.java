package com.asquarep.bloggingrestapi.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PostDTO {
    private Long id;
    private String title;
    private String body;
    private String imageUrl;
    private String communityName;

}
