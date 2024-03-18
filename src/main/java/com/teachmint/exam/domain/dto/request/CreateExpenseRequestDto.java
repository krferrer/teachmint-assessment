package com.teachmint.exam.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.teachmint.exam.domain.enums.ExpenseSplitType;
import jakarta.validation.constraints.Max;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@JsonTypeInfo(use= JsonTypeInfo.Id.NAME,include = JsonTypeInfo.As.EXISTING_PROPERTY,defaultImpl = CreateExpenseRequestDto.class,property = "type",visible = true)
@JsonSubTypes(value = {
        @JsonSubTypes.Type(value = CreateEqualExpenseRequestDto.class,name = "EQUAL"),
        @JsonSubTypes.Type(value = CreatePercentExpenseRequestDto.class,name = "PERCENT"),
        @JsonSubTypes.Type(value = CreateExactExpenseRequestDto.class,name = "EXACT")
})
public class CreateExpenseRequestDto {


    private Long creatorId;

    private String description;

    @Max(value = 1000000, message = "Amount must be less than or equal to 1,000,000")
    private BigDecimal amount;

    private ExpenseSplitType type;

}
