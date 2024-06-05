package com.encurtator.link.service;

import com.encurtator.link.dto.request.UrlRequest;
import com.encurtator.link.dto.response.UrlResponse;

import com.encurtator.link.model.Url;
import com.encurtator.link.repository.UrlRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UrlService {

    private final UrlRepository urlRepository;

    public UrlResponse shortenUrl(UrlRequest urlRequest) {
        String shortUrl = UrlShortener.generateShortUrl(urlRequest.originalUrl());
        Url url = new Url(urlRequest.originalUrl(), shortUrl);
        urlRepository.save(url);
        return new UrlResponse(shortUrl);
    }

    public String getOriginalUrl(String shortUrl) {
        Optional<Url> urlOptional = urlRepository.findByShortUrl(shortUrl);
        Url url = urlOptional.orElseThrow(() -> new EntityNotFoundException("URL not found"));
        urlOptional.ifPresent(this::updateUrlAccessCounter);
        return url.getOriginalUrl();
    }

    public Object getStatistics(String shortUrl) {
        return urlRepository.findByShortUrl(shortUrl).get().getAccessCount();
    }

    private void updateUrlAccessCounter(Url url){
        url.setAccessCount(url.getAccessCount() + 1);
        updateUrlModifiedAt(url);
        urlRepository.save(url);
    }

    private void updateUrlModifiedAt(Url url){
        url.setModifiedAt(LocalDateTime.now());
    }
}
