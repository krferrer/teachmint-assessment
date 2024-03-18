package com.teachmint.exam.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.teachmint.exam.commons.dto.PageDto;
import com.teachmint.exam.domain.dto.request.ExpenseSearchDto;
import com.teachmint.exam.domain.dto.request.ShareSearchDto;
import com.teachmint.exam.domain.dto.response.ParticipantBalanceDto;
import com.teachmint.exam.domain.dto.response.ParticipantExpensesDto;
import com.teachmint.exam.domain.entity.Participant;
import com.teachmint.exam.domain.enums.ParticipantType;
import com.teachmint.exam.domain.mapper.DefaultMapper;
import com.teachmint.exam.domain.repository.ExpenseRepository;
import com.teachmint.exam.domain.repository.ParticipantRepository;
import com.teachmint.exam.domain.repository.ShareRepository;
import com.teachmint.exam.service.GeneralExpenseService;
import com.teachmint.exam.service.exception.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import static com.teachmint.exam.domain.entity.QParticipant.participant;
import static com.teachmint.exam.domain.entity.QExpense.expense;
import static com.teachmint.exam.domain.entity.QShare.share;

@Service
@Slf4j
public class GeneralExpenseServiceImpl implements GeneralExpenseService {

    private ExpenseRepository expenseRepository;

    private ShareRepository shareRepository;

    private ParticipantRepository participantRepository;

    private DefaultMapper mapper;

    @Autowired
    public GeneralExpenseServiceImpl(ExpenseRepository expenseRepository, ShareRepository shareRepository, ParticipantRepository participantRepository, DefaultMapper mapper) {
        this.expenseRepository = expenseRepository;
        this.shareRepository = shareRepository;
        this.participantRepository = participantRepository;
        this.mapper = mapper;
    }

    @Override
    public ParticipantExpensesDto findUserExpenses(ExpenseSearchDto searchDto) {
        var participant = participantRepository.findById(searchDto.getUserId())
                .orElseThrow(()->new EntityNotFoundException("Participant not found"));
        return mapper.toDto(participant);
    }

    @Override
    public ParticipantBalanceDto findUserBalances(ShareSearchDto searchDto) {
        log.info("ShareSearchDto: {}",searchDto);
        var participant = participantRepository.findById(searchDto.getUserId())
                .orElseThrow(()-> new EntityNotFoundException("User not found"));
        return mapper.toBalanceDto(participant);
    }


    private BooleanBuilder buildBalanceQuery(ShareSearchDto searchDto) {
        BooleanBuilder query = new BooleanBuilder();

        query.and(participant.id.eq(searchDto.getUserId())
                .and(share.participant.id.eq(participant.id)
                        .and(share.participantType.eq(ParticipantType.PARTICIPANT))));
        return query;
    }
}
