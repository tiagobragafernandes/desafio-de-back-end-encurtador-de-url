package com.encurtator.link.infrastructure.rateLimiter;

public interface RateLimiter {
    boolean isRateLimited(String key);
}
