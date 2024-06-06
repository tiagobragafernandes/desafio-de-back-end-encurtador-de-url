package com.encurtator.link.application.rest;

import com.encurtator.link.application.response.UrlAllTimeAccessResponse;
import com.encurtator.link.application.response.UrlAverageAccessResponse;
import com.encurtator.link.domain.service.UrlStatisticService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/urlStatistics")
@RequiredArgsConstructor
@Tag(name = "URL Statistics Controller", description = "Endpoints for retrieving URL access statistics")
public class UrlStatisticsController {

    private final UrlStatisticService urlStatisticService;

    @Operation(summary = "Get all-time statistics", description = "Retrieves all-time access statistics for a given shortened URL")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Statistics retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UrlAllTimeAccessResponse.class))),
            @ApiResponse(responseCode = "404", description = "Shortened URL not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping("allTime/{id}")
    public ResponseEntity<UrlAllTimeAccessResponse> getStatistics(@PathVariable String id) {
        var allTimeStatistics = urlStatisticService.getUrlStatisticsOfAllTime(id);
        return ResponseEntity.ok(allTimeStatistics);
    }

    @Operation(summary = "Get average statistics", description = "Retrieves average daily access statistics for a given shortened URL over a specified period")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Statistics retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UrlAverageAccessResponse.class))),
            @ApiResponse(responseCode = "404", description = "Shortened URL not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping("average/{id}/{period}")
    public ResponseEntity<UrlAverageAccessResponse> getStatistics(@PathVariable String id, @PathVariable Integer period) {
        var allTimeStatistics = urlStatisticService.calculateAverageStatistics(id, period);
        return ResponseEntity.ok(allTimeStatistics);
    }
}
