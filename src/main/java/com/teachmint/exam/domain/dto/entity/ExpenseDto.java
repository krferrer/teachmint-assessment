package com.teachmint.exam.domain.dto.entity;

import com.teachmint.exam.domain.entity.Participant;
import com.teachmint.exam.domain.entity.Share;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ExpenseDto {

    private Long id;

    private Participant creator;

    private String description;

    private BigDecimal amount;

    private List<Share> shares;
}
