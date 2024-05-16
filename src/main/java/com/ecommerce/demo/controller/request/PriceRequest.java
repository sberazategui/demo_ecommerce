package com.ecommerce.demo.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PriceRequest {

    private Long productId;
    private LocalDateTime date;
    private Long brandId;
}
