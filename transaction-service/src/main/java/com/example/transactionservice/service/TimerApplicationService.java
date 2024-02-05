package com.example.transactionservice.service;

import com.example.transactionservice.repository.DailyDataEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class TimerApplicationService {
    @Autowired
    private DailyDataService dailyDataService;
    @Autowired
    private DailyDataEntityRepository dailyDataEntityRepository;


    @Scheduled(fixedRate = 120000)
    public void fetchInformation() {
        dailyDataEntityRepository.deleteAll();
        dailyDataService.getAction();
        System.out.println("Таймер сработал! Получаю информацию...");
    }
}
