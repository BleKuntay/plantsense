package com.iot.plantsense.sensor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorService {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public List<SensorModel> getAllSensors() {
        return sensorRepository.findAll();
    }

    public SensorModel getLatestSensorData() {
        return sensorRepository.findTopByOrderByTimestampDesc();
    }

    public void saveSensor(SensorModel sensor) {
        sensorRepository.save(sensor);
    }

}
