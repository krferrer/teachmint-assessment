package com.teachmint.exam.domain.dto.request;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class CreateParticipantDto {

    private String name;
    private String email;
    private String mobileNumber;
}
