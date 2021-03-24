package com.krzysiek.social.service;

import com.krzysiek.social.dto.SubsocialDto;
import com.krzysiek.social.exceptions.SubsocialNotFoundException;
import com.krzysiek.social.model.Subsocial;
import com.krzysiek.social.repository.SubsocialRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.time.Instant.now;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class SubsocialService {

    private final SubsocialRepository subsocialRepository;
    private final AuthService authService;

    @Transactional
    public SubsocialDto save(SubsocialDto subsocialDto) {
        Subsocial save = subsocialRepository.save(mapSubsocialDto(subsocialDto));
        subsocialDto.setId(save.getId());
        return subsocialDto;
    }

    @Transactional
    public Subsocial mapSubsocialDto(SubsocialDto subsocialDto) {
        return Subsocial.builder().name(subsocialDto.getName())
                .description(subsocialDto.getDescription())
                .build();
    }

    @Transactional(readOnly = true)
    public SubsocialDto getSubsocial(Long id) {
        Subsocial subsocial = subsocialRepository.findById(id)
                .orElseThrow(() -> new SubsocialNotFoundException("Subsocial not found with id - " + id));
        return mapToDto(subsocial);
    }

    @Transactional(readOnly = true)
    public List<SubsocialDto> getAll() {
        return subsocialRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(toList());
    }

    private SubsocialDto mapToDto(Subsocial subsocial) {
        return SubsocialDto.builder().name(subsocial.getName())
                .id(subsocial.getId())
                .numberOfPosts(subsocial.getPosts().size())
                .build();
    }

    private Subsocial mapToSubsocial(SubsocialDto subsocialDto) {
        return Subsocial.builder().name("/r/" + subsocialDto.getName())
                .description(subsocialDto.getDescription())
                .user(authService.getCurrentUser())
                .createdDate(now()).build();
    }
}
