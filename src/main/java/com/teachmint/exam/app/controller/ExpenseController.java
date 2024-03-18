package com.teachmint.exam.app.controller;

import com.teachmint.exam.domain.dto.request.CreateExpenseRequestDto;
import com.teachmint.exam.domain.dto.request.ExpenseSearchDto;
import com.teachmint.exam.domain.dto.response.ResponseDto;
import com.teachmint.exam.service.ExpenseFacade;
import com.teachmint.exam.service.GeneralExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {

    private ExpenseFacade expenseFacade;

    private GeneralExpenseService generalExpenseService;


    @Autowired
    public ExpenseController(ExpenseFacade expenseFacade, GeneralExpenseService generalExpenseService) {
        this.expenseFacade = expenseFacade;
        this.generalExpenseService = generalExpenseService;
    }


    @PostMapping("/find")
    public ResponseEntity findParticipants(@RequestBody ExpenseSearchDto searchDto){
        return ResponseEntity.ok(ResponseDto.builder()
                .data(generalExpenseService.findUserExpenses(searchDto))
                .build());
    }

    @PostMapping("/create")
    public ResponseEntity findParticipants(@Valid @RequestBody CreateExpenseRequestDto requestDto){
        return ResponseEntity.ok(ResponseDto.builder()
                .data(expenseFacade.createExpense(requestDto))
                .build());
    }


}
