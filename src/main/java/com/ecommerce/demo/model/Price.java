package com.ecommerce.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name="prices")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Price {

    /*
    * Campos:

BRAND_ID: foreign key de la cadena del grupo (1 = ZARA).
START_DATE ,
* END_DATE: rango de fechas en el que aplica el precio tarifa indicado.
PRICE_LIST: Identificador de la tarifa de precios aplicable.
PRODUCT_ID: Identificador código de producto.
PRIORITY: Desambiguador de aplicación de precios. Si dos tarifas coinciden en un rago de fechas se aplica la de mayor prioridad (mayor valor numérico).
PRICE: precio final de venta.
CURR: iso de la moneda.
    *
    * */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer brandId;
    private Integer priceListId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private String currencyIso;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "fare_id")
    private Fare fare;

    @Override
    public String toString() {
        return "Price{" +
                "id=" + id +
                ", brandId=" + brandId +
                ", priceListId=" + priceListId +
                ", currencyIso='" + currencyIso + '\'' +
                ", price=" + price +
                '}';
    }


}
