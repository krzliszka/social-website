package com.krzysiek.social.mapper;

import com.krzysiek.social.dto.PostRequest;
import com.krzysiek.social.dto.PostResponse;
import com.krzysiek.social.model.Post;
import com.krzysiek.social.model.Post.PostBuilder;
import com.krzysiek.social.model.Subsocial;
import com.krzysiek.social.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-03-29T00:29:24+0200",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.7 (Amazon.com Inc.)"
)
@Component
public class PostMapperImpl extends PostMapper {

    @Override
    public Post map(PostRequest postRequest, Subsocial subsocial, User user) {
        if ( postRequest == null && subsocial == null && user == null ) {
            return null;
        }

        PostBuilder post = Post.builder();

        if ( postRequest != null ) {
            post.description( postRequest.getDescription() );
            post.postId( postRequest.getPostId() );
            post.postName( postRequest.getPostName() );
            post.url( postRequest.getUrl() );
        }
        if ( subsocial != null ) {
            post.subsocial( subsocial );
            post.user( subsocial.getUser() );
        }
        post.createdDate( java.time.Instant.now() );
        post.voteCount( 0 );

        return post.build();
    }

    @Override
    public PostResponse mapToDto(Post post) {
        if ( post == null ) {
            return null;
        }

        PostResponse postResponse = new PostResponse();

        postResponse.setSubsocialName( postSubsocialName( post ) );
        postResponse.setId( post.getPostId() );
        postResponse.setUserName( postUserUsername( post ) );
        postResponse.setPostName( post.getPostName() );
        postResponse.setUrl( post.getUrl() );
        postResponse.setDescription( post.getDescription() );
        postResponse.setVoteCount( post.getVoteCount() );

        postResponse.setDuration( getDuration(post) );
        postResponse.setCommentCount( commentCount(post) );

        return postResponse;
    }

    private String postSubsocialName(Post post) {
        if ( post == null ) {
            return null;
        }
        Subsocial subsocial = post.getSubsocial();
        if ( subsocial == null ) {
            return null;
        }
        String name = subsocial.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String postUserUsername(Post post) {
        if ( post == null ) {
            return null;
        }
        User user = post.getUser();
        if ( user == null ) {
            return null;
        }
        String username = user.getUsername();
        if ( username == null ) {
            return null;
        }
        return username;
    }
}
