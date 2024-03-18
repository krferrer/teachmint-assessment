package com.teachmint.exam.domain.dto.entity;

import com.teachmint.exam.domain.entity.Expense;
import com.teachmint.exam.domain.entity.Share;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class ParticipantDto {

    private Long id;
    private String name;
    private String email;
    private String mobileNumber;
    private List<Expense> expenses;
    private List<Share> shares;
}
