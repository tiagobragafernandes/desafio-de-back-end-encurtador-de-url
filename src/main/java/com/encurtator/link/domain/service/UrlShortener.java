package com.encurtator.link.domain.service;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class UrlShortener {

    private static final int SHORT_URL_LENGTH = 10;
    private static final Random RANDOM = new Random();

    public static String generateShortUrl(String originalUrl) {
        return RANDOM.ints(SHORT_URL_LENGTH, 0, 10)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining());
    }
}
