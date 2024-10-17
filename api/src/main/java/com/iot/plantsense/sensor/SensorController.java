package com.iot.plantsense.sensor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sensors")
public class SensorController {

    private SensorService sensorService;

    @Autowired
    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @GetMapping("/all")
    public List<SensorModel> getAllSensors() {
        return sensorService.getAllSensors();
    }

    @GetMapping("/latest")
    public SensorModel getLatestSensors() {
        return sensorService.getLatestSensorData();
    }

    @PostMapping
    public ResponseEntity<String> receiveSensorData(@RequestBody SensorModel sensor) {
        System.out.println("Received moisture: " + sensor.getMoisture());

        sensorService.saveSensor(sensor);
        return ResponseEntity.ok("Data received successfully");
    }

}
