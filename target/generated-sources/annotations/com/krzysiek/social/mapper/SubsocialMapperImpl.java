package com.krzysiek.social.mapper;

import com.krzysiek.social.dto.SubsocialDto;
import com.krzysiek.social.dto.SubsocialDto.SubsocialDtoBuilder;
import com.krzysiek.social.model.Subsocial;
import com.krzysiek.social.model.Subsocial.SubsocialBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-03-28T00:58:02+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.7 (Amazon.com Inc.)"
)
@Component
public class SubsocialMapperImpl implements SubsocialMapper {

    @Override
    public SubsocialDto mapSubsocialToDto(Subsocial subsocial) {
        if ( subsocial == null ) {
            return null;
        }

        SubsocialDtoBuilder subsocialDto = SubsocialDto.builder();

        subsocialDto.id( subsocial.getId() );
        subsocialDto.name( subsocial.getName() );
        subsocialDto.description( subsocial.getDescription() );

        subsocialDto.numberOfPosts( mapPosts(subsocial.getPosts()) );

        return subsocialDto.build();
    }

    @Override
    public Subsocial mapDtoToSubsocial(SubsocialDto subsocialDto) {
        if ( subsocialDto == null ) {
            return null;
        }

        SubsocialBuilder subsocial = Subsocial.builder();

        subsocial.id( subsocialDto.getId() );
        subsocial.name( subsocialDto.getName() );
        subsocial.description( subsocialDto.getDescription() );

        return subsocial.build();
    }
}
