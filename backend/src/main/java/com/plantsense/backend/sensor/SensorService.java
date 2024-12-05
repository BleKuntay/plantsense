package com.plantsense.backend.sensor;

import com.plantsense.backend.sensor.dto.DailySummaryDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SensorService implements SensorServiceInterface {
    private final SensorRepository sensorRepository;

    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }


    @Override
    public void saveSensorData(SensorEntity sensorEntity) {
        sensorEntity.setTimestamp(LocalDateTime.now());
        sensorRepository.save(sensorEntity);
    }

    @Override
    public List<SensorEntity> getAllSensorData() {
        return sensorRepository.findAll();
    }

    @Override
    public void deleteOldSensorData(LocalDateTime date) {
        sensorRepository.deleteByTimestampBefore(date);
    }

    public List<DailySummaryDTO> getDailySummary() {
        LocalDateTime sevenDaysAgo = LocalDateTime.now().minusDays(7);
        return sensorRepository.findDailySummary(sevenDaysAgo);
    }
}
