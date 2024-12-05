package com.plantsense.backend.sensor.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class DailySummaryDTO {
    private LocalDate day;
    private Double avgTemperature;
    private Double avgHumidity;
    private Double avgSoilMoisture;

    public DailySummaryDTO(LocalDate day, Double avgTemperature, Double avgHumidity, Double avgSoilMoisture) {
        this.day = day;
        this.avgTemperature = avgTemperature;
        this.avgHumidity = avgHumidity;
        this.avgSoilMoisture = avgSoilMoisture;
    }

}
