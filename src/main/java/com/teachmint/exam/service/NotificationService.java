package com.teachmint.exam.service;

import com.teachmint.exam.domain.dto.request.NotificationRequestDto;

public interface NotificationService {

    void sendNotification(NotificationRequestDto requestDto);
}
