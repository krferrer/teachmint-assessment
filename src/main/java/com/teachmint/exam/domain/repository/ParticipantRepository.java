package com.teachmint.exam.domain.repository;

import com.teachmint.exam.commons.repository.BaseJpaRepository;
import com.teachmint.exam.domain.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends BaseJpaRepository<Participant,Long> {
}
