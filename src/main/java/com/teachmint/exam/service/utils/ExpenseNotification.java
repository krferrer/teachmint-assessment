package com.teachmint.exam.service.utils;

import com.teachmint.exam.domain.dto.request.NotificationRequestDto;
import com.teachmint.exam.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExpenseNotification {
    private NotificationService notificationService;

    @Autowired
    public ExpenseNotification(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void sendExpenseNotification(String to, String creatorName,
                                  String description,
                                        String amount){
        var FROM = "no-reply@test.com";
        var message = String.format("You are part of expense '%s', you now owe '%s' to %s", description, amount, creatorName);
        notificationService.sendNotification(NotificationRequestDto.builder()
                        .to(List.of(to))
                        .from(FROM)
                        .subject("New Expense Alert")
                        .body(message)
                .build());
    }
}
