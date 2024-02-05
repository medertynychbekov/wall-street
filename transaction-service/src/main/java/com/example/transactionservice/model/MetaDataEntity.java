package com.example.transactionservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "metaData")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MetaDataEntity {
    @Id
    private Long id;
    private String information;

    private String symbol;

    private String lastRefreshed;

    private String outputSize;

    private String timeZone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "metaDataEntity")
    private List<DailyDataEntity> dailyDataList;
}
