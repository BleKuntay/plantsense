package com.plantsense.backend.sensor;

import java.time.LocalDateTime;
import java.util.List;

public interface SensorServiceInterface {
    void saveSensorData(SensorEntity sensorEntity);
    List<SensorEntity> getAllSensorData();
    void deleteOldSensorData(LocalDateTime date);
}
