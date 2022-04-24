package com.asquarep.bloggingrestapi.service;

import com.asquarep.bloggingrestapi.dto.CommunityDTO;
import com.asquarep.bloggingrestapi.model.Community;

import java.util.List;
import java.util.Optional;

public interface CommunityService {
    Optional<CommunityDTO> createCommunity(CommunityDTO communityDTO, long bloggerId);
    Optional<CommunityDTO> editCommunity(CommunityDTO communityDTO);
    List<CommunityDTO> getAllCommunities();
    Optional<CommunityDTO> getCommunityById(long communityId);
}
