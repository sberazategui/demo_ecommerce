
package com.ecommerce.demo.controller;

import com.ecommerce.demo.controller.request.PriceRequest;
import com.ecommerce.demo.controller.response.PriceResponse;
import com.ecommerce.demo.service.PriceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class EcommerceController {

    @Autowired
   private PriceServices priceServices;

    @GetMapping("/price")
    @ExceptionHandler(Exception.class)
    public PriceResponse getFinalProductPrice(@RequestParam Long productId,
                                              @RequestParam Long brandId,
                                              @RequestParam LocalDateTime date) {
        return priceServices.getProductPrice(new PriceRequest(productId, date, brandId));
    }
}

