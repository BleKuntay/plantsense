package com.iot.plantsense.sensor;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Setter
@Getter
@Document(collection = "sensors")
public class SensorModel {

    @Id
    private String id;
    private double temperature;
    private double humidity;
    private double moisture;
    private LocalDateTime timestamp;

    public SensorModel() {
        this.timestamp = LocalDateTime.now();
    }
}
