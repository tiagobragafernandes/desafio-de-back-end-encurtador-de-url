package com.encurtator.link.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
    @OneToMany(mappedBy = "url", cascade = CascadeType.ALL)
    private List<UrlStatistics> statistics;
    @Column(nullable = false)
    private Boolean isActive;

    public Url(String originalUrl, String shortUrl) {
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
        this.isActive = true;
    }
}
