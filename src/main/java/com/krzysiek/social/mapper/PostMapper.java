package com.krzysiek.social.mapper;

import com.krzysiek.social.dto.PostRequest;
import com.krzysiek.social.dto.PostResponse;
import com.krzysiek.social.model.Post;
import com.krzysiek.social.model.Subsocial;
import com.krzysiek.social.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target = "subsocial", source = "subsocial")
    @Mapping(target = "user", source = "user")
    Post map(PostRequest postRequest, Subsocial subsocial, User user);


    @Mapping(target = "id", source = "postId")
    @Mapping(target = "subsocialName", source = "subsocial.name")
    @Mapping(target = "userName", source = "user.username")
    PostResponse mapToDto(Post post);
}
