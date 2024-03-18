package com.teachmint.exam.commons.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseJpaRepository<T, ID extends Serializable>
        extends JpaRepository<T, ID>, PagingAndSortingRepository<T, ID>, QuerydslPredicateExecutor<T> {

}
