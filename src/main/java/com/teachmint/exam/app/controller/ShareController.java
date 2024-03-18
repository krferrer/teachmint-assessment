package com.teachmint.exam.app.controller;

import com.teachmint.exam.domain.dto.request.ExpenseSearchDto;
import com.teachmint.exam.domain.dto.request.ShareSearchDto;
import com.teachmint.exam.domain.dto.response.ResponseDto;
import com.teachmint.exam.service.ExpenseFacade;
import com.teachmint.exam.service.GeneralExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/share")
public class ShareController {

    private GeneralExpenseService generalExpenseService;


    @Autowired
    public ShareController(GeneralExpenseService generalExpenseService) {
        this.generalExpenseService = generalExpenseService;
    }

    @PostMapping("/find")
    public ResponseEntity findShare(@RequestBody ShareSearchDto searchDto){
        return ResponseEntity.ok(ResponseDto.builder()
                .data(generalExpenseService.findUserBalances(searchDto))
                .build());
    }
}
