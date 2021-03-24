package com.krzysiek.social.repository;


import com.krzysiek.social.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
  //  List<Post> findAllBySubreddit(Subsocial subsocial);

  //  List<Post> findByUser(User user);
}