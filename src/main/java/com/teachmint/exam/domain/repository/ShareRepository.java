package com.teachmint.exam.domain.repository;

import com.teachmint.exam.commons.repository.BaseJpaRepository;
import com.teachmint.exam.domain.entity.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShareRepository extends BaseJpaRepository<Share,Long> {


    @Query("SELECT s FROM Share s " +
            "JOIN s.participant p " +
            "WHERE p.id = :userId " +
            "AND s.participantType <> com.teachmint.exam.domain.enums.ParticipantType.CREATOR")
    List<Share> findSharesNotCreatorById(@Param("userId") Long userId);
}
