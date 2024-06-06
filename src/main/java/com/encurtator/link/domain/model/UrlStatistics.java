package com.encurtator.link.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "UrlStatistics")
@Table(name = "url_statistics")
@Getter
@Setter
@NoArgsConstructor
public class UrlStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "url_id")
    private Url url;
    @Column(nullable = false)
    private LocalDateTime accessDate;
    @Column(nullable = false)
    private Integer accessCount;

}
