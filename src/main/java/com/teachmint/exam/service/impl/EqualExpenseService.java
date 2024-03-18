package com.teachmint.exam.service.impl;

import com.teachmint.exam.domain.dto.entity.ExpenseDto;
import com.teachmint.exam.domain.dto.request.CreateEqualExpenseRequestDto;
import com.teachmint.exam.domain.dto.request.CreateExpenseRequestDto;
import com.teachmint.exam.domain.entity.Expense;
import com.teachmint.exam.domain.entity.Participant;
import com.teachmint.exam.domain.entity.Share;
import com.teachmint.exam.domain.enums.ExpenseSplitType;
import com.teachmint.exam.domain.enums.ParticipantType;
import com.teachmint.exam.domain.mapper.DefaultMapper;
import com.teachmint.exam.domain.repository.ExpenseRepository;
import com.teachmint.exam.domain.repository.ParticipantRepository;
import com.teachmint.exam.domain.repository.ShareRepository;
import com.teachmint.exam.service.ExpenseService;
import com.teachmint.exam.service.exception.EntityNotFoundException;
import com.teachmint.exam.service.utils.ExpenseNotification;
import com.teachmint.exam.service.utils.ShareUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EqualExpenseService implements ExpenseService {

    private ParticipantRepository participantRepository;

    private ShareRepository shareRepository;

    private DefaultMapper mapper;
    private final ExpenseRepository expenseRepository;

    private ExpenseNotification expenseNotification;


    @Autowired
    public EqualExpenseService(ParticipantRepository participantRepository, ShareRepository shareRepository, DefaultMapper mapper, ExpenseRepository expenseRepository, ExpenseNotification expenseNotification) {
        this.participantRepository = participantRepository;
        this.shareRepository = shareRepository;
        this.mapper = mapper;
        this.expenseRepository = expenseRepository;
        this.expenseNotification = expenseNotification;
    }

    @Override
    public ExpenseDto createExpense(CreateExpenseRequestDto requestDto) {
        var request = (CreateEqualExpenseRequestDto)requestDto;
        var newExpense = mapper.toEntity(request);
        var totalAmount = request.getAmount();
        var creator = participantRepository.findById(request.getCreatorId())
                .orElseThrow(()-> new EntityNotFoundException("Participant not found"));
        var participants = participantRepository.findAllById(request.getParticipantIds());
        var participantSize = participants.size() + 1;

        var equalAmount = ShareUtils.calculateEqualShare(request.getAmount(),participantSize);
        var remainingAmount = ShareUtils.calculateRemainingAmount(totalAmount,equalAmount,participantSize);

        var participantShares = buildParticipantShares(participants,equalAmount,newExpense);
        participantShares.add(buildCreatorShare(creator,remainingAmount,newExpense));

        newExpense.setCreator(creator);
        newExpense.setShares(participantShares);

        var savedExpense = expenseRepository.save(newExpense);
        sendNotification(savedExpense);
        return mapper.toDto(savedExpense);
    }

    @Override
    public boolean supports(ExpenseSplitType type) {
        return type.equals(ExpenseSplitType.EQUAL);
    }


    public Share buildCreatorShare(Participant participant,BigDecimal remainingAmount, Expense expense){
        return Share.builder()
                .participant(participant)
                .participantType(ParticipantType.CREATOR)
                .amount(remainingAmount)
                .expense(expense)
                .build();
    }

    public List<Share> buildParticipantShares(List<Participant> participants, BigDecimal equalShare, Expense expense){
        return participants.stream().map(participant -> Share.builder()
                .participant(participant)
                .participantType(ParticipantType.PARTICIPANT)
                .amount(equalShare)
                .expense(expense)
                .build()).collect(Collectors.toList());
    }

    private void sendNotification(Expense expense){
        expense.getShares()
                .stream()
                .forEach(share ->{
                    if(!share.getParticipantType().equals(ParticipantType.CREATOR)){
                        expenseNotification.sendExpenseNotification(share.getParticipant().getEmail(),
                                expense.getCreator().getName(), expense.getDescription(),
                                expense.getAmount().toString());
                    }
                });
    }
}
