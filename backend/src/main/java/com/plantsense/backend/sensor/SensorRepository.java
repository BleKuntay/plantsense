package com.plantsense.backend.sensor;

import com.plantsense.backend.sensor.dto.DailySummaryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SensorRepository extends JpaRepository<SensorEntity, Integer> {
    void deleteByTimestampBefore(LocalDateTime timestamp);

    List<SensorEntity> findByTimestampBetween(LocalDateTime start, LocalDateTime end);

    @Query("SELECT new com.plantsense.backend.sensor.dto.DailySummaryDTO(CAST(s.timestamp AS LocalDate), AVG(s.temperature), AVG(s.humidity), AVG(s.soilMoisture)) " +
            "FROM SensorEntity s " +
            "WHERE s.timestamp >= :cutoffDate " +
            "GROUP BY CAST(s.timestamp AS LocalDate) " +
            "ORDER BY CAST(s.timestamp AS LocalDate) DESC")
    List<DailySummaryDTO> findDailySummary(@Param("cutoffDate") LocalDateTime cutoffDate);
}
