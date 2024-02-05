package com.example.transactionservice.service;

import com.example.transactionservice.model.AlphaVantageResponse;
import com.example.transactionservice.model.DailyData;
import com.example.transactionservice.model.DailyDataEntity;
import com.example.transactionservice.model.MetaDataEntity;
import com.example.transactionservice.repository.DailyDataEntityRepository;
import com.example.transactionservice.repository.MetaDataRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DailyDataService {
    DailyDataEntityRepository dailyDataEntityRepository;
    MetaDataRepository metaDataRepository;
    private static final String API_URL = "https://www.alphavantage.co/query";
    private static final String FUNCTION = "TIME_SERIES_DAILY";
    private static final String SYMBOL = "IBM";
    private static final String API_KEY = "demo";

    RestTemplate restTemplate = new RestTemplate();
@Transactional
    public void getAction() {

        String URL = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=IBM&apikey=demo";
        ResponseEntity<AlphaVantageResponse> responseEntity = restTemplate.getForEntity(URL, AlphaVantageResponse.class);

        AlphaVantageResponse alphaVantageResponse = responseEntity.getBody();
        assert alphaVantageResponse != null;
        saveDailData(alphaVantageResponse);

    }

    public void saveDailData(AlphaVantageResponse alphaVantageResponse) {

        MetaDataEntity metaDataEntity = saveMetaDataEntity(alphaVantageResponse);

        List<DailyDataEntity> dailyDataEntityList = new ArrayList<>();

        long id = 1;
        for (Map.Entry<String, DailyData> entry : alphaVantageResponse.getTimeSeriesDaily().getDailyDataMap().entrySet()) {
            DailyData dailyData1 = entry.getValue();
            DailyDataEntity dailyDataEntity = new DailyDataEntity();
            dailyDataEntity.setId(id);
            dailyDataEntity.setOpen(dailyData1.getOpen());
            dailyDataEntity.setLow(dailyData1.getLow());
            dailyDataEntity.setHigh(dailyData1.getHigh());
            dailyDataEntity.setClose(dailyData1.getClose());
            dailyDataEntity.setVolume(dailyData1.getVolume());
            dailyDataEntity.setDate(dailyData1.getDate());
            dailyDataEntityList.add(dailyDataEntity);
            dailyDataEntity.setMetaDataEntity(metaDataEntity);
            dailyDataEntityRepository.save(dailyDataEntity);
            id++;
        }
        metaDataEntity.setDailyDataList(dailyDataEntityList);
        metaDataRepository.save(metaDataEntity);
    }

    public MetaDataEntity saveMetaDataEntity (AlphaVantageResponse alphaVantageResponse){
        MetaDataEntity metaDataEntity = new MetaDataEntity();
        long id = 1;
        metaDataEntity.setId(id);
        metaDataEntity.setInformation(alphaVantageResponse.getMetaData().getInformation());
        metaDataEntity.setSymbol(alphaVantageResponse.getMetaData().getSymbol());
        metaDataEntity.setLastRefreshed(alphaVantageResponse.getMetaData().getLastRefreshed());
        metaDataEntity.setOutputSize(alphaVantageResponse.getMetaData().getOutputSize());
        metaDataEntity.setTimeZone(alphaVantageResponse.getMetaData().getTimeZone());
        return metaDataEntity;
    }

    private static String buildUrl() {
        return String.format("%s?function=%s&symbol=%s&apikey=%s", API_URL, FUNCTION, SYMBOL, API_KEY);
    }
}
