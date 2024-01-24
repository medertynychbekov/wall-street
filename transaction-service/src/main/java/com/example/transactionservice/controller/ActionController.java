package com.example.transactionservice.controller;

import com.example.transactionservice.service.ActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/actions")
public class ActionController {
    ActionService actionService;
@Autowired
    public ActionController(ActionService actionService) {
        this.actionService = actionService;
    }

    @GetMapping("/getAction")
    public String getAction() {
        System.out.println("User ");
        actionService.getAction();
        return "suuuu";
    }
}
