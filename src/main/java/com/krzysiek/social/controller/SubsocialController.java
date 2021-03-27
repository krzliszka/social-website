package com.krzysiek.social.controller;

import com.krzysiek.social.dto.SubsocialDto;
import com.krzysiek.social.service.SubsocialService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/subsocial")
@AllArgsConstructor
@Slf4j
public class SubsocialController {

    private final SubsocialService subsocialService;

    @PostMapping
    public ResponseEntity<SubsocialDto> createSubsocial(@RequestBody SubsocialDto subsocialDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(subsocialService.save(subsocialDto));
    }

    @GetMapping
    public ResponseEntity<List<SubsocialDto>> getAllSubsocials() {
        return ResponseEntity.status(HttpStatus.OK).body(subsocialService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubsocialDto> getSubsocial(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(subsocialService.getSubsocial(id));
    }

}
