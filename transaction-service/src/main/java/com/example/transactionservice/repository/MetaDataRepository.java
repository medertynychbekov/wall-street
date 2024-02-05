package com.example.transactionservice.repository;

import com.example.transactionservice.model.MetaDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetaDataRepository extends JpaRepository<MetaDataEntity,Long> {
}
