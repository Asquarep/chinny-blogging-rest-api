package com.asquarep.bloggingrestapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CommunityDTO {
    private String communityName;
    private String communityDescription;
}
