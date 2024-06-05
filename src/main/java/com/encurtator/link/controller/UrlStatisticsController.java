package com.encurtator.link.controller;

import com.encurtator.link.dto.response.UrlAllTimeAccessResponse;
import com.encurtator.link.dto.response.UrlAverageAccessResponse;
import com.encurtator.link.service.UrlStatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/urlStatistics")
@RequiredArgsConstructor
public class UrlStatisticsController {

    private final UrlStatisticService urlStatisticService;

    @GetMapping("allTime/{id}")
    public ResponseEntity<UrlAllTimeAccessResponse> getStatistics(@PathVariable String id) {
        var allTimeStatistics = urlStatisticService.getUrlStatisticsOfAllTime(id);
        return ResponseEntity.ok(allTimeStatistics);
    }

    @GetMapping("average/{id}/{period}")
    public ResponseEntity<UrlAverageAccessResponse> getStatistics(@PathVariable String id, @PathVariable Integer period) {
        var allTimeStatistics = urlStatisticService.calculateAverageStatistics(id, period);
        return ResponseEntity.ok(allTimeStatistics);
    }
}
