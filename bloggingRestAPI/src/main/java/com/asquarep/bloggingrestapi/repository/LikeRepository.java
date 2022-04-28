package com.asquarep.bloggingrestapi.repository;

import com.asquarep.bloggingrestapi.model.Like;
import com.asquarep.bloggingrestapi.model.Post;
import com.asquarep.bloggingrestapi.model.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findLikeByPostAndReader(Post post, Reader reader);
}
