package com.krzysiek.social.mapper;

import com.krzysiek.social.dto.SubsocialDto;
import com.krzysiek.social.model.Post;
import com.krzysiek.social.model.Subsocial;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubsocialMapper {

    @Mapping(target = "numberOfPosts", expression = "java(mapPosts(subsocial.getPosts()))")
    SubsocialDto mapSubsocialToDto(Subsocial subsocial);

    default Integer mapPosts(List<Post> numberOfPosts) {
        return numberOfPosts.size();
    }

    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    Subsocial mapDtoToSubsocial(SubsocialDto subsocialDto);
}