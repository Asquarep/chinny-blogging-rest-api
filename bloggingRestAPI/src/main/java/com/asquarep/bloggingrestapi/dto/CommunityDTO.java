package com.asquarep.bloggingrestapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommunityDTO {
    private Long id;
    private String communityName;
    private String communityDescription;

    public CommunityDTO(String communityName, String communityDescription) {
        this.communityName = communityName;
        this.communityDescription = communityDescription;
    }
}
