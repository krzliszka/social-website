package com.krzysiek.social.service;

import com.krzysiek.social.dto.PostRequest;
import com.krzysiek.social.dto.PostResponse;
import com.krzysiek.social.exceptions.PostNotFoundException;
import com.krzysiek.social.exceptions.SubsocialNotFoundException;
import com.krzysiek.social.mapper.PostMapper;
import com.krzysiek.social.model.Post;
import com.krzysiek.social.model.Subsocial;
import com.krzysiek.social.model.User;
import com.krzysiek.social.repository.PostRepository;
import com.krzysiek.social.repository.SubsocialRepository;
import com.krzysiek.social.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final SubsocialRepository subsocialRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final PostMapper postMapper;

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id.toString()));
        return postMapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }

    public void save(PostRequest postRequest) {
        Subsocial subsocial = subsocialRepository.findByName(postRequest.getSubsocialName())
                .orElseThrow(() -> new SubsocialNotFoundException(postRequest.getSubsocialName()));

        postRepository.save(postMapper.map(postRequest, subsocial, authService.getCurrentUser()));
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubsocial(Long subsocialId) {
        Subsocial subsocial = subsocialRepository.findById(subsocialId)
                .orElseThrow(() -> new SubsocialNotFoundException(subsocialId.toString()));
        List<Post> posts = postRepository.findAllBySubsocial(subsocial);
        return posts.stream().map(postMapper::mapToDto).collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return postRepository.findByUser(user)
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }
}