package com.teachmint.exam.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "participant_id")
    @JsonIgnore
    private Participant creator;

    private String description;

    private BigDecimal amount;

    @OneToMany(cascade = {CascadeType.PERSIST})
    private List<Share> shares;



}
