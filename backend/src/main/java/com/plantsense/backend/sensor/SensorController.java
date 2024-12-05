package com.plantsense.backend.sensor;

import com.plantsense.backend.sensor.dto.DailySummaryDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/sensors")
public class SensorController {
    private final SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @PostMapping
    public ResponseEntity<String> saveSensorData(@RequestBody SensorEntity sensorEntity) {
        sensorService.saveSensorData(sensorEntity);
        return ResponseEntity.ok("Sensor saved");
    }

    @GetMapping
    public ResponseEntity<List<SensorEntity>> getAllSensors() {
        return ResponseEntity.ok(sensorService.getAllSensorData());
    }

    @GetMapping("/summary")
    public ResponseEntity<List<DailySummaryDTO>> getDailySummary() {
        List<DailySummaryDTO> summary = sensorService.getDailySummary();
        return ResponseEntity.ok(summary);
    }
}
