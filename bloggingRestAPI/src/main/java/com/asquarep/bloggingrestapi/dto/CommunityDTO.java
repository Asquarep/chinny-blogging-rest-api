package com.asquarep.bloggingrestapi.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CommunityDTO {
    private Long id;
    private String communityName;
    private String communityDescription;

    public CommunityDTO(String communityName, String communityDescription) {
        this.communityName = communityName;
        this.communityDescription = communityDescription;
    }
}
