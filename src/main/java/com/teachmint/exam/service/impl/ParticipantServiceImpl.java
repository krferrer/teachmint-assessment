package com.teachmint.exam.service.impl;

import com.teachmint.exam.domain.dto.entity.ParticipantDto;
import com.teachmint.exam.domain.dto.request.CreateParticipantDto;
import com.teachmint.exam.domain.entity.Participant;
import com.teachmint.exam.domain.mapper.DefaultMapper;
import com.teachmint.exam.domain.repository.ParticipantRepository;
import com.teachmint.exam.service.ParticipantService;
import com.teachmint.exam.service.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipantServiceImpl implements ParticipantService {

    private ParticipantRepository repository;

    private DefaultMapper mapper;

    @Autowired
    public ParticipantServiceImpl(ParticipantRepository repository, DefaultMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<Participant> findAll() {
        return repository.findAll();
    }

    @Override
    public ParticipantDto findParticipant(Long id) {
        var participant = repository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Participant not found"));
        return mapper.toParticipantDto(participant);
    }

    @Override
    public ParticipantDto createParticipant(CreateParticipantDto requestDto) {
        var user = mapper.createParticipant(requestDto);
        return mapper.toParticipantDto(repository.save(user));
    }
}
