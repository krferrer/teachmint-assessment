package com.teachmint.exam.service;

import com.teachmint.exam.commons.dto.PageDto;
import com.teachmint.exam.domain.dto.request.ExpenseSearchDto;
import com.teachmint.exam.domain.dto.request.ShareSearchDto;
import com.teachmint.exam.domain.dto.response.ParticipantBalanceDto;
import com.teachmint.exam.domain.dto.response.ParticipantExpensesDto;
import com.teachmint.exam.domain.entity.Participant;

public interface GeneralExpenseService {
    ParticipantExpensesDto findUserExpenses(ExpenseSearchDto searchDto);

    ParticipantBalanceDto findUserBalances(ShareSearchDto searchDto);

}
