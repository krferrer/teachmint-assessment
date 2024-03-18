package com.teachmint.exam.domain.repository;

import com.teachmint.exam.commons.repository.BaseJpaRepository;
import com.teachmint.exam.domain.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends BaseJpaRepository<Expense,Long> {
}
