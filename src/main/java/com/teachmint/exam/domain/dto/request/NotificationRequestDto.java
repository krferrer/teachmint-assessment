package com.teachmint.exam.domain.dto.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class NotificationRequestDto {

    private String from;
    private List<String> to;
    private String subject;
    private String body;
}
