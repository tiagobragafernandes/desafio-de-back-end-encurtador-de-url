package com.encurtator.link.service;

import com.encurtator.link.dto.request.UrlRequest;
import com.encurtator.link.dto.response.UrlResponse;
import com.encurtator.link.exception.UrlNotFoundException;
import com.encurtator.link.model.Url;
import com.encurtator.link.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UrlService {

    private final UrlRepository urlRepository;
    private final UrlStatisticService urlStatisticService;

    @Value("${api.domain-base}")
    private String domainBase;

    public UrlResponse shortenUrl(UrlRequest urlRequest) {
        String shortUrl = UrlShortener.generateShortUrl(urlRequest.originalUrl());
        Url url = new Url(urlRequest.originalUrl(), shortUrl);
        urlRepository.save(url);
        String fullShortUrl = domainBase + "/" + shortUrl;
        return new UrlResponse(fullShortUrl);
    }

    public String getOriginalUrl(String shortUrl) {
        Optional<Url> urlOptional = urlRepository.findByShortUrl(shortUrl);
        Url url = urlOptional.orElseThrow(() -> new UrlNotFoundException("URL not found"));
        urlStatisticService.updateUrlAccessCounter(url);
        return url.getOriginalUrl();
    }
}
