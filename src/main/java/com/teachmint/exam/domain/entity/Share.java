package com.teachmint.exam.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teachmint.exam.domain.enums.ParticipantType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Share {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "participant_id")
    @JsonIgnore
    private Participant participant;

    private ParticipantType participantType;

    @ManyToOne
    @JoinColumn(name = "expense_id")
    @JsonIgnore
    private Expense expense;

    private BigDecimal amount;

}
