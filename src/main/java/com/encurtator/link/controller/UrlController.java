package com.encurtator.link.controller;

import com.encurtator.link.dto.request.UrlRequest;
import com.encurtator.link.dto.response.UrlResponse;
import com.encurtator.link.service.UrlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@Tag(name = "URL Controller", description = "Endpoints for URL shortening and redirection")
public class UrlController {

    private final UrlService urlService;

    @Operation(summary = "Shorten a URL", description = "Generates a shortened URL for the given original URL")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "URL shortened successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UrlResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid URL request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @PostMapping("/api/url/shorten")
    public ResponseEntity<UrlResponse> shortenUrl(@RequestBody UrlRequest urlRequest) {
        var response = urlService.shortenUrl(urlRequest);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Redirect to original URL",
            description = "Redirects the user to the original URL based on the shortened URL"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Redirection to original URL"),
            @ApiResponse(responseCode = "404", description = "Shortened URL not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> redirect(@PathVariable String shortUrl) {
        String originalUrl = urlService.getOriginalUrl(shortUrl);
        return ResponseEntity.status(302).location(URI.create(originalUrl)).build();
    }
}
