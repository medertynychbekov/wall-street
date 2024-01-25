package com.example.transactionservice.service;

import com.example.transactionservice.model.AlphaVantageResponse;
import com.example.transactionservice.model.DailyData;
import com.example.transactionservice.model.DailyDataEntity;
import com.example.transactionservice.repository.DailyDataEntityRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class DailyDataService {

    public DailyDataService(DailyDataEntityRepository dailyDataEntityRepository) {
        this.dailyDataEntityRepository = dailyDataEntityRepository;
    }

    DailyDataEntityRepository dailyDataEntityRepository;
    private final String URL = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=IBM&apikey=demo";

    private static final String API_URL = "https://www.alphavantage.co/query";
    private static final String FUNCTION = "TIME_SERIES_DAILY";
    private static final String SYMBOL = "IBM";
    private static final String API_KEY = "demo";

    RestTemplate restTemplate = new RestTemplate();

    public void getAction() {

        ResponseEntity<AlphaVantageResponse> responseEntity = restTemplate.getForEntity(URL, AlphaVantageResponse.class);

        AlphaVantageResponse alphaVantageResponse = responseEntity.getBody();
        assert alphaVantageResponse != null;
        saveDailData(alphaVantageResponse);

    }

    public void saveDailData(AlphaVantageResponse alphaVantageResponse) {


        for (Map.Entry<String, DailyData> entry : alphaVantageResponse.getTimeSeriesDaily().getDailyDataMap().entrySet()) {
            String key = entry.getKey();
            DailyData dailyData1 = entry.getValue();
            DailyDataEntity dailyDataEntity = new DailyDataEntity();
            dailyDataEntity.setOpen(dailyData1.getOpen());
            dailyDataEntity.setLow(dailyData1.getLow());
            dailyDataEntity.setHigh(dailyData1.getHigh());
            dailyDataEntity.setClose(dailyData1.getClose());
            dailyDataEntity.setVolume(dailyData1.getVolume());
            dailyDataEntity.setDate(dailyData1.getDate());
            dailyDataEntityRepository.save(dailyDataEntity);
        }

    }

    private static String buildUrl() {
        return String.format("%s?function=%s&symbol=%s&apikey=%s", API_URL, FUNCTION, SYMBOL, API_KEY);
    }
}
