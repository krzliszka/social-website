package com.krzysiek.social.repository;

import com.krzysiek.social.model.Comment;
import com.krzysiek.social.model.Post;
import com.krzysiek.social.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByUser(User user);

    List<Comment> findByPost(Post post);
}
