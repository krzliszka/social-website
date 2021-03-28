package com.krzysiek.social.repository;

import com.krzysiek.social.model.Post;
import com.krzysiek.social.model.User;
import com.krzysiek.social.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}
