package com.example.transactionservice.service;

import com.example.transactionservice.model.AlphaVantageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ActionService {
    private final String URL = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=IBM&apikey=demo";

    private static final String API_URL = "https://www.alphavantage.co/query";
    private static final String FUNCTION = "TIME_SERIES_DAILY";
    private static final String SYMBOL = "IBM";
    private static final String API_KEY = "demo";

    RestTemplate restTemplate = new RestTemplate();

    public void getAction() {

        ResponseEntity<AlphaVantageResponse> responseEntity = restTemplate.getForEntity(buildUrl(), AlphaVantageResponse.class);

        AlphaVantageResponse alphaVantageResponse = responseEntity.getBody();

        System.out.println(alphaVantageResponse);
    }
    private static String buildUrl() {
        return String.format("%s?function=%s&symbol=%s&apikey=%s", API_URL, FUNCTION, SYMBOL, API_KEY);
    }
}
