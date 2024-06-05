package com.encurtator.link.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Url")
@Table(name = "url")
@Getter
@Setter
@NoArgsConstructor
public class Url extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String originalUrl;
    @Column(nullable = false, unique = true)
    private String shortUrl;
    @Column(nullable = false)
    private Integer accessCount;

    public Url(String originalUrl, String shortUrl) {
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
        this.accessCount = 0;
    }
}
