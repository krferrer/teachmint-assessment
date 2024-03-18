package com.teachmint.exam.service.impl;

import com.teachmint.exam.domain.dto.entity.ExpenseDto;
import com.teachmint.exam.domain.dto.request.CreateExactExpenseRequestDto;
import com.teachmint.exam.domain.dto.request.CreateExpenseRequestDto;
import com.teachmint.exam.domain.dto.request.CreatePercentExpenseRequestDto;
import com.teachmint.exam.domain.dto.request.ParticipantShare;
import com.teachmint.exam.domain.entity.Expense;
import com.teachmint.exam.domain.entity.Share;
import com.teachmint.exam.domain.enums.ExpenseSplitType;
import com.teachmint.exam.domain.enums.ParticipantType;
import com.teachmint.exam.domain.mapper.DefaultMapper;
import com.teachmint.exam.domain.repository.ExpenseRepository;
import com.teachmint.exam.domain.repository.ParticipantRepository;
import com.teachmint.exam.domain.repository.ShareRepository;
import com.teachmint.exam.service.ExpenseService;
import com.teachmint.exam.service.exception.EntityNotFoundException;
import com.teachmint.exam.service.exception.InvalidPayloadException;
import com.teachmint.exam.service.utils.ExpenseNotification;
import com.teachmint.exam.service.utils.ShareUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExactExpenseService implements ExpenseService {


    private ParticipantRepository participantRepository;

    private ShareRepository shareRepository;

    private DefaultMapper mapper;
    private ExpenseRepository expenseRepository;

    private ExpenseNotification expenseNotification;

    @Autowired
    public ExactExpenseService(ParticipantRepository participantRepository, ShareRepository shareRepository, DefaultMapper mapper, ExpenseRepository expenseRepository, ExpenseNotification expenseNotification) {
        this.participantRepository = participantRepository;
        this.shareRepository = shareRepository;
        this.mapper = mapper;
        this.expenseRepository = expenseRepository;
        this.expenseNotification = expenseNotification;
    }

    @Override
    public ExpenseDto createExpense(CreateExpenseRequestDto requestDto) {
        var request = (CreateExactExpenseRequestDto)requestDto;
        validateShareAmount(request.getShares(),request.getAmount());

        var newExpense = mapper.toEntity(request);
        var creator = participantRepository.findById(request.getCreatorId())
                .orElseThrow(()-> new EntityNotFoundException("Participant not found"));

        var shares = buildShares(request,newExpense);
        newExpense.setShares(shares);
        newExpense.setCreator(creator);
        var savedExpense = expenseRepository.save(newExpense);
        sendNotification(savedExpense);
        return mapper.toDto(savedExpense);
    }

    @Override
    public boolean supports(ExpenseSplitType type) {
        return type.equals(ExpenseSplitType.EXACT);
    }

    private List<Share> buildShares(CreateExactExpenseRequestDto requestDto, Expense expense){
        var shares =  new ArrayList<Share>();

        requestDto.getShares().stream().forEach(participantShare ->{
            var participant = participantRepository.findById(participantShare.getUserId())
                    .orElseThrow(()-> new EntityNotFoundException("Participant not found"));
            shares.add(Share.builder()
                    .participant(participant)
                    .participantType(requestDto.getCreatorId().equals(participantShare.getUserId())
                            ? ParticipantType.CREATOR :  ParticipantType.PARTICIPANT)
                    .amount(participantShare.getShareAmount())
                    .expense(expense)
                    .build());
        });
        return shares;
    }


    private void validateShareAmount(List<ParticipantShare> shares, BigDecimal totalAmount) {
        var totalShareAmount = shares.stream()
                .map(ParticipantShare::getShareAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (totalShareAmount.compareTo(totalAmount) != 0) {
            throw new InvalidPayloadException("Total share amount does not match the total amount");
        }
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
