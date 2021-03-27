package com.krzysiek.social.repository;


import com.krzysiek.social.model.Post;
import com.krzysiek.social.model.Subsocial;
import com.krzysiek.social.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllBySubsocial(Subsocial subsocial);

    List<Post> findByUser(User user);
}