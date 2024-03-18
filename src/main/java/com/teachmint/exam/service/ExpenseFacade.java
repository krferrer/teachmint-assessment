package com.teachmint.exam.service;

import com.teachmint.exam.domain.dto.entity.ExpenseDto;
import com.teachmint.exam.domain.dto.request.CreateExpenseRequestDto;
import com.teachmint.exam.domain.enums.ExpenseSplitType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExpenseFacade {

    private List<ExpenseService> expenseServiceList;

    @Autowired
    public ExpenseFacade(List<ExpenseService> expenseServiceList) {
        this.expenseServiceList = expenseServiceList;
    }

    public ExpenseDto createExpense(CreateExpenseRequestDto requestDto){
        return getService(requestDto.getType()).createExpense(requestDto);
    };

    private ExpenseService getService(ExpenseSplitType type){
        return expenseServiceList.stream()
                .filter(expenseService -> expenseService.supports(type)).findFirst()
                .orElseThrow(()-> new RuntimeException("Split method not present"));
    }
}
