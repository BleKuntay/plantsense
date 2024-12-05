package com.plantsense.backend.summary;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "daily_average")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SummaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(nullable = false, name = "avg_soil_moisture")
    double avgSoilMoisture;

    @Column(nullable = false, name = "avg_humidity")
    double avgHumidity;

    @Column(nullable = false, name = "avg_temperature")
    double avgTemperature;

    @Column(nullable = false)
    LocalDate date;
}
