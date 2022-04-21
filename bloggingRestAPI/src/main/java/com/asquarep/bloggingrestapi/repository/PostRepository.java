package com.asquarep.bloggingrestapi.repository;

import com.asquarep.bloggingrestapi.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
//    Post findByTitleAndPostedBy(long id);
}
