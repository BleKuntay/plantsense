package com.iot.plantsense.sensor;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends MongoRepository<SensorModel, String> {
    SensorModel findTopByOrderByTimestampDesc();
}
