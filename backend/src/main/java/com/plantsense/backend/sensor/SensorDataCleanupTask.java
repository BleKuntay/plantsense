package com.plantsense.backend.sensor;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SensorDataCleanupTask {
    private final SensorService sensorService;

    public SensorDataCleanupTask(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Scheduled(cron = "0 0 2 * * ?")
    public void cleanupOldSensorData() {
        try {
            LocalDateTime sevenDaysAgo = LocalDateTime.now().minusDays(7);
            sensorService.deleteOldSensorData(sevenDaysAgo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("cleanupOldSensorData function executed.");
        }
    }
}
