package com.example.transactionservice.repository;

import com.example.transactionservice.model.DailyDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyDataEntityRepository extends JpaRepository<DailyDataEntity, Long> {
}
