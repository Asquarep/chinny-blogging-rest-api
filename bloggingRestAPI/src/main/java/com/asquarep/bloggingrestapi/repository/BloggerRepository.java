package com.asquarep.bloggingrestapi.repository;

import com.asquarep.bloggingrestapi.model.Blogger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BloggerRepository extends JpaRepository<Blogger, Long> {
    Optional<Blogger> findBloggerByEmail(String email);
}
