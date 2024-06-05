package com.encurtator.link.repository;

import com.encurtator.link.model.UrlStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlStatisticsRepository extends JpaRepository<UrlStatistics, Long> {
}
