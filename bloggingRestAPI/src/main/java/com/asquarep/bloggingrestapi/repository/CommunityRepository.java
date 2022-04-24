package com.asquarep.bloggingrestapi.repository;

import com.asquarep.bloggingrestapi.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {
    Optional<Community> findByCommunityName(String name);
//    Optional<Community> findCommunityBy
}
