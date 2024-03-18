package com.teachmint.exam.service;

import com.teachmint.exam.domain.dto.entity.ParticipantDto;
import com.teachmint.exam.domain.dto.request.CreateParticipantDto;
import com.teachmint.exam.domain.entity.Participant;

import java.util.List;

public interface ParticipantService {
    List<Participant> findAll();

    ParticipantDto findParticipant(Long id);

    ParticipantDto createParticipant(CreateParticipantDto requestDto);
}
