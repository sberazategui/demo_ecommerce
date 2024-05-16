package com.ecommerce.demo.service;

import com.ecommerce.demo.controller.request.PriceRequest;
import com.ecommerce.demo.controller.response.PriceResponse;
import com.ecommerce.demo.exception.FareNotFoundException;
import com.ecommerce.demo.model.Fare;
import com.ecommerce.demo.model.Price;
import com.ecommerce.demo.repository.BrandRepository;
import com.ecommerce.demo.repository.FareRepository;
import com.ecommerce.demo.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public interface PriceServices {

    PriceResponse getProductPrice(PriceRequest request);

    PriceResponse convertToPriceResponse(Price price);

    BigDecimal calculatePVP(Price price);

    @Service
    class PriceServiceImpl implements PriceServices {

        @Autowired
        private PriceRepository priceRepository;

        @Autowired
        private FareRepository fareRepository;

        @Autowired
        private BrandRepository brandRepository;

        @Override
        @Transactional(readOnly = true)
        public PriceResponse getProductPrice(PriceRequest request) {
            return convertToPriceResponse(priceRepository.findById(request.getProductId()).orElseThrow());
        }

        @Override
        public PriceResponse convertToPriceResponse(Price price) {

            PriceResponse priceResponse = new PriceResponse();

            if (price != null) {

                priceResponse.setProductId(price.getProduct().getId());
                priceResponse.setBrandId(price.getBrandId());
                priceResponse.setFare(price.getPriceListId());
                priceResponse.setPvp(calculatePVP(price));
                priceResponse.setDate(LocalDateTime.now().toString());
                priceResponse.setBrand(getBrandById(price.getBrandId()));
            }
            return priceResponse;
        }

        private String getBrandById(Integer brandId) {

            return brandRepository.getReferenceById(Long.valueOf(brandId)).getName();
        }


        @Override
        public BigDecimal calculatePVP(Price price) {

            Date currentDate = new Date(System.currentTimeMillis());
            List<Fare> fares = fareRepository.getByCurrentDate(currentDate);

            if (fares == null || fares.isEmpty()) {
                throw new FareNotFoundException("No Fare Available.");
            }

            Fare maxPriorityFare = fares.stream()
                    .max(Comparator.comparingInt(Fare::getPriority))
                    .orElseThrow(() -> new FareNotFoundException("No Fare Available with valid priority."));

            //Caso Tarifa Oferta:
            //Calcula la tarifa final en modo oferta.
            //El campo fareValue será el porcentaje a aplicar en caso de oferta.
            BigDecimal percentage = BigDecimal.valueOf(maxPriorityFare.getFareValue()).divide(BigDecimal.valueOf(100));
            BigDecimal fareApplied = price.getPrice().multiply(percentage);

            return price.getPrice().subtract(fareApplied);

        }
    }
}
