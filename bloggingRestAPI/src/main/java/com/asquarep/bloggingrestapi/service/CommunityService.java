package com.asquarep.bloggingrestapi.service;

import com.asquarep.bloggingrestapi.dto.CommunityDTO;
import com.asquarep.bloggingrestapi.model.Community;

import java.util.List;

public interface CommunityService {
    Community createCommunity(CommunityDTO communityDTO, long bloggerId);
    Community editCommunity(CommunityDTO communityDTO);
    List<Community> getAllComunities();
}
