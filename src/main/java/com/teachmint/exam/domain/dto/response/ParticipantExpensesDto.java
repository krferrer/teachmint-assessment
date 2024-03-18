package com.teachmint.exam.domain.dto.response;

import com.teachmint.exam.domain.entity.Expense;
import lombok.Data;

import java.util.List;

@Data
public class ParticipantExpensesDto {
    private Long id;
    private String name;

    private List<Expense> expenses;
}
