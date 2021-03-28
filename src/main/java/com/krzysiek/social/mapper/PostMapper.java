package com.krzysiek.social.mapper;

import com.github.marlonlom.utilities.timeago.TimeAgo;
import com.krzysiek.social.dto.PostRequest;
import com.krzysiek.social.dto.PostResponse;
import com.krzysiek.social.model.Post;
import com.krzysiek.social.model.Subsocial;
import com.krzysiek.social.model.User;
import com.krzysiek.social.repository.CommentRepository;
import com.krzysiek.social.repository.VoteRepository;
import com.krzysiek.social.service.AuthService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class PostMapper {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private AuthService authService;


    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target = "subsocial", source = "subsocial")
    @Mapping(target = "voteCount", constant = "0")
    public abstract Post map(PostRequest postRequest, Subsocial subsocial, User user);

    @Mapping(target = "id", source = "postId")
    @Mapping(target = "subsocialName", source = "subsocial.name")
    @Mapping(target = "userName", source = "user.username")
    @Mapping(target = "commentCount", expression = "java(commentCount(post))")
    @Mapping(target = "duration", expression = "java(getDuration(post))")
    public abstract PostResponse mapToDto(Post post);

    Integer commentCount(Post post) {
        return commentRepository.findByPost(post).size();
    }

    String getDuration(Post post) {
        return TimeAgo.using(post.getCreatedDate().toEpochMilli());
    }
}
