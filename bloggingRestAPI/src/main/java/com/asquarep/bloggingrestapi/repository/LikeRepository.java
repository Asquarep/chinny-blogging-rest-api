package com.asquarep.bloggingrestapi.repository;

import com.asquarep.bloggingrestapi.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
}
