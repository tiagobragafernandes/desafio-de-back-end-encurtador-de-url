package com.encurtator.link.domain.service;

import com.encurtator.link.application.response.UrlAllTimeAccessResponse;
import com.encurtator.link.application.response.UrlAverageAccessResponse;
import com.encurtator.link.domain.repository.UrlRepository;
import com.encurtator.link.domain.repository.UrlStatisticsRepository;
import com.encurtator.link.domain.exception.UrlNotFoundException;
import com.encurtator.link.domain.model.Url;
import com.encurtator.link.domain.model.UrlStatistics;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UrlStatisticService {

    private final UrlRepository urlRepository;
    private final UrlStatisticsRepository urlStatisticsRepository;

    public UrlAllTimeAccessResponse getUrlStatisticsOfAllTime(String shortUrl) {
        var statisticsList = urlRepository.findByShortUrl(shortUrl)
                .orElseThrow(() -> new UrlNotFoundException("URL not found"))
                .getStatistics();
        var allTimeStats = calculateAllTimeStatistics(statisticsList);
        return new UrlAllTimeAccessResponse(allTimeStats);
    }

    public UrlAverageAccessResponse calculateAverageStatistics(String shortUrl, Integer numberOfDays) {
        var statisticsList = urlRepository.findByShortUrl(shortUrl)
                .orElseThrow(() -> new UrlNotFoundException("URL not found"))
                .getStatistics();
        var averageStats = calculateAverageDailyStatistics(statisticsList, numberOfDays);
        return new UrlAverageAccessResponse(averageStats);
    }

    public void updateUrlAccessCounter(Url url){
        UrlStatistics statistics = new UrlStatistics();
        statistics.setUrl(url);
        statistics.setAccessDate(LocalDateTime.now());
        statistics.setAccessCount(1);
        urlStatisticsRepository.save(statistics);
    }

    private long calculateAllTimeStatistics(List<UrlStatistics> statisticsList) {
        return statisticsList.stream()
                .mapToLong(UrlStatistics::getAccessCount)
                .sum();
    }

    private double calculateAverageDailyStatistics(List<UrlStatistics> statisticsList, Integer numberOfDays) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime startDateTime = currentDateTime.minusDays(numberOfDays);

        List<UrlStatistics> filteredStatistics = statisticsList.stream()
                .filter(statistics -> statistics.getAccessDate().isAfter(startDateTime))
                .toList();

        var totalDays = startDateTime.toLocalDate().datesUntil(currentDateTime.toLocalDate()).count();
        var totalAccesses = filteredStatistics.stream()
                .mapToInt(UrlStatistics::getAccessCount)
                .sum();

        var averageAccesses = totalAccesses / totalDays;

        return averageAccesses;
    }
}
