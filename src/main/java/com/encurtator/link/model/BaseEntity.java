package com.encurtator.link.model;


import java.time.LocalDateTime;

public abstract class BaseEntity {
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private boolean isActive;
}
