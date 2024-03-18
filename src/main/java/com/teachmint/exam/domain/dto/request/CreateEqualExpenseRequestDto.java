package com.teachmint.exam.domain.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class CreateEqualExpenseRequestDto extends CreateExpenseRequestDto{
    private List<Long> participantIds;
}
