package com.krzysiek.social.service;

import com.krzysiek.social.dto.SubsocialDto;
import com.krzysiek.social.exceptions.SubsocialNotFoundException;
import com.krzysiek.social.mapper.SubsocialMapper;
import com.krzysiek.social.model.Subsocial;
import com.krzysiek.social.repository.SubsocialRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class SubsocialService {

    private final SubsocialRepository subsocialRepository;
    private final SubsocialMapper subsocialMapper;

    @Transactional
    public SubsocialDto save(SubsocialDto subsocialDto) {
        Subsocial save = subsocialRepository.save(subsocialMapper.mapDtoToSubsocial(subsocialDto));
        subsocialDto.setId(save.getId());
        return subsocialDto;
    }

    @Transactional(readOnly = true)
    public List<SubsocialDto> getAll() {
        return subsocialRepository.findAll()
                .stream()
                .map(subsocialMapper::mapSubsocialToDto)
                .collect(toList());
    }

    @Transactional
    public SubsocialDto getSubsocial(Long id) {
        Subsocial subsocial = subsocialRepository.findById(id)
                .orElseThrow(() -> new SubsocialNotFoundException("No subsocial found with ID - " + id));
        return subsocialMapper.mapSubsocialToDto(subsocial);
    }
}