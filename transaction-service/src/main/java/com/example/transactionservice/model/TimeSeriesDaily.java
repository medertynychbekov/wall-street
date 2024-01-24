package com.example.transactionservice.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TimeSeriesDaily {

    @JsonAnySetter
    private Map<String, DailyData> dailyDataMap;

    public Map<String, DailyData> getDailyDataMap() {
        return dailyDataMap;
    }

    public void setDailyDataMap(Map<String, DailyData> dailyDataMap) {
        this.dailyDataMap = dailyDataMap;
    }
}
