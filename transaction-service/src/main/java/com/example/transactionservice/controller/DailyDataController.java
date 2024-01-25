package com.example.transactionservice.controller;

import com.example.transactionservice.service.DailyDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/actions")
public class DailyDataController {
    DailyDataService dailyDataService;

    @Autowired
    public DailyDataController(DailyDataService dailyDataService) {
        this.dailyDataService = dailyDataService;
    }

    @GetMapping("/getAction")
    public String getAction() {
        System.out.println("User ");
        dailyDataService.getAction();
        return "suuuu";
    }
}
