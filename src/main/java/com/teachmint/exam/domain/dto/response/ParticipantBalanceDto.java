package com.teachmint.exam.domain.dto.response;

import com.teachmint.exam.domain.entity.Expense;
import com.teachmint.exam.domain.entity.Share;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class ParticipantBalanceDto {
    private String name;

    private List<Share> shares;
}
