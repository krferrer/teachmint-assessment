package com.teachmint.exam.service;

import com.teachmint.exam.domain.dto.entity.ExpenseDto;
import com.teachmint.exam.domain.dto.request.CreateExpenseRequestDto;
import com.teachmint.exam.domain.enums.ExpenseSplitType;

public interface ExpenseService {

    ExpenseDto createExpense(CreateExpenseRequestDto requestDto);

    boolean supports(ExpenseSplitType type);



}
