package com.asquarep.bloggingrestapi.repository;

import com.asquarep.bloggingrestapi.model.Blogger;
import com.asquarep.bloggingrestapi.model.Community;
import com.asquarep.bloggingrestapi.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
//    Post findByTitleAndPostedBy(long id);

    Optional<Post> findByPostIdAndAndPostedBy(long postId, Blogger blogger);
    List<Post> findAllByPostedBy(Blogger blogger);
    List<Post> findAllByCommunity(Community community);
}
