package com.ecommerce.demo;

import com.ecommerce.demo.controller.EcommerceController;
import com.ecommerce.demo.controller.request.PriceRequest;
import com.ecommerce.demo.controller.response.PriceResponse;
import com.ecommerce.demo.repository.BrandRepository;
import com.ecommerce.demo.repository.PriceRepository;
import com.ecommerce.demo.service.PriceServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EcommerceController.class)
@AutoConfigureMockMvc
public class EcommerceControllerIntegrationTest {

    private static final Logger logger = LoggerFactory.getLogger(EcommerceControllerIntegrationTest.class);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PriceServices priceServices;

    @MockBean
    private PriceRepository priceRepository;

    @MockBean
    private BrandRepository brandRepository;

    @Test
    void shouldReturnCorrectPrice1() throws Exception {
        Long productId = 35455L;
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 00, 00);
        Integer brandId = 1;

        PriceResponse mockResponse = new PriceResponse();
        mockResponse.setProductId(productId);
        mockResponse.setBrandId(brandId);
        mockResponse.setFare(123);
        mockResponse.setDate(String.valueOf(date));
        mockResponse.setPvp(BigDecimal.valueOf(100.50));
        mockResponse.setBrand("ZARA");


        when(priceServices.getProductPrice(any(PriceRequest.class))).thenReturn(mockResponse);

        mockMvc.perform(get("/price")
                        .param("productId", productId.toString())
                        .param("date", date.toString())
                        .param("brandId", brandId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(productId))
                .andExpect(jsonPath("$.brandId").value(brandId))
                .andExpect(jsonPath("$.fare").value(mockResponse.getFare()))
                .andExpect(jsonPath("$.pvp").value(mockResponse.getPvp()));

        logger.info
                ("petición a las " + date.getHour() + " del día " + date.getDayOfMonth()
                        + " del producto " + mockResponse.getProductId()
                        + " para la cadena "
                        +  mockResponse.getBrandId()
                        + "( " + mockResponse.getBrand());

    }

    @Test
    void shouldReturnCorrectPrice2() throws Exception {
        Long productId = 35455L;
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 16, 00, 00);
        Integer brandId = 1;

        PriceResponse mockResponse = new PriceResponse();
        mockResponse.setProductId(productId);
        mockResponse.setBrandId(brandId);
        mockResponse.setFare(123);
        mockResponse.setDate(String.valueOf(date));
        mockResponse.setPvp(BigDecimal.valueOf(100.50));
        mockResponse.setBrand("ZARA");

        when(priceServices.getProductPrice(any(PriceRequest.class))).thenReturn(mockResponse);

        mockMvc.perform(get("/price")
                        .param("productId", productId.toString())
                        .param("date", date.toString())
                        .param("brandId", brandId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(productId))
                .andExpect(jsonPath("$.brandId").value(brandId))
                .andExpect(jsonPath("$.fare").value(mockResponse.getFare()))
                .andExpect(jsonPath("$.pvp").value(mockResponse.getPvp()));

        logger.info
                ("petición a las " + date.getHour() + " del día " + date.getDayOfMonth()
                        + " del producto " + mockResponse.getProductId()
                        + " para la cadena "
                        +  mockResponse.getBrandId()
                        + "( " + mockResponse.getBrand() + " ) "  + " pvp: " + jsonPath("$.pvp"));
    }


    @Test
    void shouldReturnCorrectPrice3() throws Exception {
        Long productId = 35455L;
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 21, 00, 00);
        Integer brandId = 1;

        PriceResponse mockResponse = new PriceResponse();
        mockResponse.setProductId(productId);
        mockResponse.setBrandId(brandId);
        mockResponse.setFare(123);
        mockResponse.setDate(String.valueOf(date));
        mockResponse.setPvp(BigDecimal.valueOf(100.50));
        mockResponse.setBrand("ZARA");

        when(priceServices.getProductPrice(any(PriceRequest.class))).thenReturn(mockResponse);

        mockMvc.perform(get("/price")
                        .param("productId", productId.toString())
                        .param("date", date.toString())
                        .param("brandId", brandId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(productId))
                .andExpect(jsonPath("$.brandId").value(brandId))
                .andExpect(jsonPath("$.fare").value(mockResponse.getFare()))
                .andExpect(jsonPath("$.pvp").value(mockResponse.getPvp()));

        logger.info
                ("petición a las " + date.getHour() + " del día " + date.getDayOfMonth()
                        + " del producto " + mockResponse.getProductId()
                        + " para la cadena "
                        +  mockResponse.getBrandId()
                        + "( " + mockResponse.getBrand() + " ) "  + " pvp: " + jsonPath("$.pvp"));
    }

    @Test
    void shouldReturnCorrectPrice4() throws Exception {
        Long productId = 35455L;
        LocalDateTime date = LocalDateTime.of(2020, 6, 15, 10, 00, 00);
        Integer brandId = 1;

        PriceResponse mockResponse = new PriceResponse();
        mockResponse.setProductId(productId);
        mockResponse.setBrandId(brandId);
        mockResponse.setFare(123);
        mockResponse.setDate(String.valueOf(date));
        mockResponse.setPvp(BigDecimal.valueOf(100.50));
        mockResponse.setBrand("ZARA");

        when(priceServices.getProductPrice(any(PriceRequest.class))).thenReturn(mockResponse);

        mockMvc.perform(get("/price")
                        .param("productId", productId.toString())
                        .param("date", date.toString())
                        .param("brandId", brandId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(productId))
                .andExpect(jsonPath("$.brandId").value(brandId))
                .andExpect(jsonPath("$.fare").value(mockResponse.getFare()))
                .andExpect(jsonPath("$.pvp").value(mockResponse.getPvp()));

        logger.info
                ("petición a las " + date.getHour() + " del día " + date.getDayOfMonth()
                        + " del producto " + mockResponse.getProductId()
                        + " para la cadena "
                        +  mockResponse.getBrandId()
                        + "( " + mockResponse.getBrand() + " ) "  + " pvp: " + jsonPath("$.pvp"));
    }

    @Test
    void shouldReturnCorrectPrice5() throws Exception {
        Long productId = 35455L;
        LocalDateTime date = LocalDateTime.of(2020, 6, 16, 21, 00, 00);
        Integer brandId = 1;

        PriceResponse mockResponse = new PriceResponse();
        mockResponse.setProductId(productId);
        mockResponse.setBrandId(brandId);
        mockResponse.setFare(123);
        mockResponse.setDate(String.valueOf(date));
        mockResponse.setPvp(BigDecimal.valueOf(100.50));
        mockResponse.setBrand("ZARA");

        when(priceServices.getProductPrice(any(PriceRequest.class))).thenReturn(mockResponse);

        mockMvc.perform(get("/price")
                        .param("productId", productId.toString())
                        .param("date", date.toString())
                        .param("brandId", brandId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(productId))
                .andExpect(jsonPath("$.brandId").value(brandId))
                .andExpect(jsonPath("$.fare").value(mockResponse.getFare()))
                .andExpect(jsonPath("$.pvp").value(mockResponse.getPvp()));

        logger.info
                ("petición a las " + date.getHour() + " del día " + date.getDayOfMonth()
                        + " del producto " + mockResponse.getProductId()
                        + " para la cadena "
                        +  mockResponse.getBrandId()
                        + "( " + mockResponse.getBrand() + " ) ");
    }

}
