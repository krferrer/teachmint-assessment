package com.teachmint.exam.domain.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ParticipantShare {
    private BigDecimal shareAmount;
    private Long userId;
}
