package com.teachmint.exam.domain.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class CreatePercentExpenseRequestDto extends CreateExpenseRequestDto {
    private List<ParticipantShare> shares;
}
