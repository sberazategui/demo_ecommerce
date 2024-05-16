package com.ecommerce.demo.controller.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PriceResponse {

    /*
    * identificador de producto,
    * identificador de cadena,
    * tarifa a aplicar,
    * fechas de aplicación y
    * precio final a aplicar. */

    private Long productId;
    private Integer brandId;
    private String brand;
    private Integer fare;
    private String date;
    private BigDecimal pvp;

}
