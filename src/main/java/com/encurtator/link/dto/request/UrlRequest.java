package com.encurtator.link.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UrlRequest(
        @JsonProperty("original_url")
        String originalUrl
) {}
