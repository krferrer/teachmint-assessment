package com.teachmint.exam.app.controller;

import com.teachmint.exam.domain.dto.request.CreateParticipantDto;
import com.teachmint.exam.domain.dto.response.ResponseDto;
import com.teachmint.exam.service.ParticipantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ParticipantController {

    private ParticipantService participantService;

    @Autowired
    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }


    @GetMapping("/api/participant/{id}")
    public ResponseEntity findParticipant(@PathVariable Long id){
        return ResponseEntity.ok(ResponseDto.builder()
                .data(participantService.findParticipant(id))
                .build());
    }

    @GetMapping("/api/participants")
    public ResponseEntity findParticipants(){
        return ResponseEntity.ok(ResponseDto.builder()
                        .data(participantService.findAll())
                .build());
    }


    @PostMapping("/api/participant/create")
    public ResponseEntity createParticipant(@RequestBody CreateParticipantDto requestDto){
        return ResponseEntity.ok(ResponseDto.builder()
                .data(participantService.createParticipant(requestDto))
                .message("User created successfully")
                .build());
    }
}
