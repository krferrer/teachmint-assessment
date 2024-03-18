package com.teachmint.exam.domain.dto.request;

import com.teachmint.exam.domain.entity.Expense;
import com.teachmint.exam.domain.entity.Share;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class ShareSearchDto {
    private Long userId;
}
