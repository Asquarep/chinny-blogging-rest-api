package com.asquarep.bloggingrestapi.service;

import com.asquarep.bloggingrestapi.dto.CommunityDTO;
import com.asquarep.bloggingrestapi.dto.PostDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommunityService {
    ResponseEntity<CommunityDTO> createCommunity(CommunityDTO communityDTO, long bloggerId);
    ResponseEntity<List<CommunityDTO>> getAllCommunities();
    ResponseEntity<CommunityDTO> getCommunityById(long communityId);
    ResponseEntity<List<PostDTO>> getAllPostsByCommunity(long communityId);
}
