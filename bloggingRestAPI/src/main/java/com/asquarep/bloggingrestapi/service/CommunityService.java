package com.asquarep.bloggingrestapi.service;

import com.asquarep.bloggingrestapi.dto.CommunityDTO;
import com.asquarep.bloggingrestapi.model.Community;

public interface CommunityService {
    Community createCommunity(CommunityDTO communityDTO);
    Community editCommunity(CommunityDTO communityDTO);
}
