package com.krzysiek.social.controller;

import com.krzysiek.social.dto.SubsocialDto;
import com.krzysiek.social.service.SubsocialService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/subsocial")
@AllArgsConstructor
@Slf4j
public class SubsocialController {

    private final SubsocialService subsocialService;

    @GetMapping
    public List<SubsocialDto> getAllSubsocials() {
        return subsocialService.getAll();
    }

    @GetMapping("/{id}")
    public SubsocialDto getSubsocial(@PathVariable Long id) {
        return subsocialService.getSubsocial(id);
    }

    @PostMapping
    public SubsocialDto create(@RequestBody @Valid SubsocialDto subsocialDto) {
        return subsocialService.save(subsocialDto);
    }


}
