package com.example.transactionservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dailyData")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DailyDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String open;

    private String high;

    private String low;

    private String close;

    private String volume;

    private String date;
}
