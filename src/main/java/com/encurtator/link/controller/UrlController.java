package com.encurtator.link.controller;

import com.encurtator.link.dto.request.UrlRequest;
import com.encurtator.link.dto.response.UrlResponse;
import com.encurtator.link.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/url")
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;

    @PostMapping("/shorten")
    public ResponseEntity<UrlResponse> shortenUrl(@RequestBody UrlRequest urlRequest) {
        var response = urlService.shortenUrl(urlRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> redirect(@PathVariable String shortUrl) {
        String originalUrl = urlService.getOriginalUrl(shortUrl);
        return ResponseEntity.status(302).location(URI.create(originalUrl)).build();
    }

    @GetMapping("/{id}/stats")
    public ResponseEntity<?> getStatistics(@PathVariable String id) {
        return ResponseEntity.ok(urlService.getStatistics(id));
    }
}
