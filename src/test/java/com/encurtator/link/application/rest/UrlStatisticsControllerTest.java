package com.encurtator.link.application.rest;

import com.encurtator.link.domain.exception.UrlNotFoundException;
import com.encurtator.link.domain.repository.UrlRepository;
import com.encurtator.link.domain.service.UrlStatisticService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UrlStatisticsControllerTest {

    @Mock
    private UrlStatisticService urlStatisticService;

    @Mock
    private UrlRepository urlRepository;

    @InjectMocks
    private UrlStatisticsController urlStatisticsController;

    @Test
    void testGetAllTimeStatistics_NotFound() {
        String id = "nonExistentId";
        when(urlStatisticService.getUrlStatisticsOfAllTime(id))
                .thenThrow(new UrlNotFoundException("URL not found"));
        try {
            urlStatisticsController.getStatistics(id);
        } catch (UrlNotFoundException e) {
            System.out.println("URL não encontrada: " + id);
        }
        verify(urlStatisticService, times(1)).getUrlStatisticsOfAllTime(id);
    }
}