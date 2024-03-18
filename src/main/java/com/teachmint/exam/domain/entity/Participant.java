package com.teachmint.exam.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String mobileNumber;

    @OneToMany(mappedBy = "creator")
    private List<Expense> expenses = new ArrayList<>();

    @OneToMany(mappedBy = "participant")
    private List<Share> shares = new ArrayList<>();

}
