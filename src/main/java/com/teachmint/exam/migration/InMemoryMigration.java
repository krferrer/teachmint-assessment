package com.teachmint.exam.migration;

import com.teachmint.exam.domain.entity.Participant;
import com.teachmint.exam.domain.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InMemoryMigration {

    private ParticipantRepository participantRepository;

    @Autowired
    public InMemoryMigration(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void contextRefreshedEvent() {
        var user1 = Participant.builder()
                .name("Kurt Ferrer")
                .email("kurtrusselferrer13@gmail.com")
                .mobileNumber("09155355570")
                .build();

        var user2 = Participant.builder()
                .name("John Doe")
                .email("kferrer11@gmail.com")
                .mobileNumber("00000000")
                .build();

        var user3 = Participant.builder()
                .name("Kath gan")
                .email("KG@gmail.com")
                .mobileNumber("00000000")
                .build();
        participantRepository.saveAll(List.of(user2,user3,user1));
    }
}
