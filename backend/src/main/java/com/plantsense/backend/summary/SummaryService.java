package com.plantsense.backend.summary;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SummaryService {
    private final SummaryRepository repository;

    public SummaryService(SummaryRepository repository) {
        this.repository = repository;
    }

    public List<SummaryEntity> getLast7DaysSummary() {
        LocalDate end = LocalDate.now();
        LocalDate start = end.minusDays(7);

        return repository.findByDateBetween(start, end);
    }

    public void deleteOldSummary() {
        repository.deleteByDateBefore(LocalDate.now().minusDays(7));
    }
}
