package com.teachmint.exam.domain.mapper;

import com.teachmint.exam.domain.dto.entity.ExpenseDto;
import com.teachmint.exam.domain.dto.entity.ParticipantDto;
import com.teachmint.exam.domain.dto.request.CreateExpenseRequestDto;
import com.teachmint.exam.domain.dto.request.CreateParticipantDto;
import com.teachmint.exam.domain.dto.response.ParticipantBalanceDto;
import com.teachmint.exam.domain.dto.response.ParticipantExpensesDto;
import com.teachmint.exam.domain.entity.Expense;
import com.teachmint.exam.domain.entity.Participant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DefaultMapper {
    Expense toEntity(CreateExpenseRequestDto expenseRequestDto);

    ExpenseDto toDto(Expense expense);

    ParticipantExpensesDto toDto(Participant participant);

    ParticipantBalanceDto toBalanceDto(Participant participant);

    ParticipantDto toParticipantDto(Participant participant);

    Participant createParticipant(CreateParticipantDto createParticipantDto);
}
