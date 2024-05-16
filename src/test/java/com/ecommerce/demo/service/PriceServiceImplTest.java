package com.ecommerce.demo.service;


import com.ecommerce.demo.exception.FareNotFoundException;
import com.ecommerce.demo.model.Fare;
import com.ecommerce.demo.model.Price;
import com.ecommerce.demo.repository.FareRepository;
import com.ecommerce.demo.repository.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PriceServiceImplTest {

    @Mock
    private PriceRepository priceRepository;

    @Mock
    private FareRepository fareRepository;

    @InjectMocks
    private PriceServices.PriceServiceImpl priceService;

    private Price price;
    private Fare fare1;
    private Fare fare2;

    @BeforeEach
    void setUp() {
        price = Price.builder()
                .id(1L)
                .brandId(1)
                .priceListId(1)
                .price(BigDecimal.valueOf(100))
                .build();

        fare1 = Fare.builder()
                .id(1L)
                .priority(1)
                .fareValue(10L)
                .startDate(new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24))
                .endDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .build();

        fare2 = Fare.builder()
                .id(2L)
                .priority(2)
                .fareValue(20L)
                .startDate(new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24))
                .endDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .build();
    }

    @Test
    void shouldCalculatePVP_withMaxPriorityFare() {
        when(fareRepository.getByCurrentDate(any(Date.class))).thenReturn(Arrays.asList(fare1, fare2));

        BigDecimal calculatedPVP = priceService.calculatePVP(price);

        BigDecimal expectedPVP = price.getPrice().subtract(price.getPrice().multiply(BigDecimal.valueOf(0.20)));
        assertEquals(expectedPVP, calculatedPVP);
    }

    @Test
    void shouldThrowException_whenNoFaresAvailable() {
        when(fareRepository.getByCurrentDate(any(Date.class))).thenReturn(List.of());

        assertThrows(FareNotFoundException.class, () -> priceService.calculatePVP(price));
    }

    @Test
    void shouldThrowException_whenFareWithMaxPriorityNotPresent() {
        when(fareRepository.getByCurrentDate(any(Date.class))).thenReturn(null);
        assertThrows(FareNotFoundException.class, () -> priceService.calculatePVP(price));
    }
}
