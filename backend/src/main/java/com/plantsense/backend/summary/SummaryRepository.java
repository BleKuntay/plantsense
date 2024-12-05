package com.plantsense.backend.summary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SummaryRepository extends JpaRepository<SummaryEntity, Integer> {
    List<SummaryEntity> findByDateBetween(LocalDate start, LocalDate end);

    void deleteByDateBefore(LocalDate date);
}
