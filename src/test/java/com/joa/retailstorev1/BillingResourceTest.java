package com.joa.retailstorev1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joa.retailstorev1.enums.CustomerUserType;
import com.joa.retailstorev1.model.Category;
import com.joa.retailstorev1.model.Customer;
import com.joa.retailstorev1.model.Product;
import com.joa.retailstorev1.model.dto.SalesDTO;
import com.joa.retailstorev1.services.BillingService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BillingResource.class)
@Slf4j
class BillingResourceTest extends BaseTest{

    SalesDTO sale;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BillingService billingService;

    @BeforeEach
    public void setup() {
        mapper.findAndRegisterModules();
    }

    @SneakyThrows
    @Test
    void testUserIsAnEmployee() {
        sale = SalesDTO.builder()
                .id(1L)
                .totalCost(new BigDecimal("2000.00"))
                .customer(Customer.builder()
                        .id(1L)
                        .firstName("Jeffrey")
                        .lastName("Adams")
                        .email("joa@gmail.com")
                        .createdAt(LocalDateTime.parse("2020-12-17T15:48:30.529059"))
                        .userType(CustomerUserType.AFFILIATE)
                        .build())
                .product(List.of(Product.builder()
                                .id(1L)
                                .price(new BigDecimal("1000.00"))
                                .description("Product 1 Description")
                                .name("Product 1")
                                .category(Category.builder()
                                        .id(1L)
                                        .build())
                                .build(),
                        Product.builder()
                                .id(2L)
                                .price(new BigDecimal("1000.00"))
                                .description("Product 2 Description")
                                .name("Product 2")
                                .category(Category.builder()
                                        .id(2L)
                                        .build())
                                .build()))
                .build();
        when(billingService.calculateDiscount(sale))
                .thenReturn(new BigDecimal("1400.00"));

        mockMvc.perform(post("/billing")
                        .content(mapper.writeValueAsString(sale))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(mockRes -> {
                    assertEquals("1400.00", mockRes.getResponse().getContentAsString());
                });
    }

    @SneakyThrows
    @Test
    void testUserIsAnAffiliate() {
        sale = SalesDTO.builder()
                .id(1L)
                .totalCost(new BigDecimal("2000.00"))
                .customer(Customer.builder()
                        .id(1L)
                        .firstName("Jeffrey")
                        .lastName("Adams")
                        .email("joa@gmail.com")
                        .createdAt(LocalDateTime.parse("2023-12-17T15:48:30.529059"))
                        .userType(CustomerUserType.AFFILIATE)
                        .build())
                .product(List.of(Product.builder()
                                .id(1L)
                                .price(new BigDecimal("1000.00"))
                                .description("Product 1 Description")
                                .name("Product 1")
                                .category(Category.builder()
                                        .id(1L)
                                        .build())
                                .build(),
                        Product.builder()
                                .id(2L)
                                .price(new BigDecimal("1000.00"))
                                .description("Product 2 Description")
                                .name("Product 2")
                                .category(Category.builder()
                                        .id(2L)
                                        .build())
                                .build()))
                .build();
        when(billingService.calculateDiscount(sale))
                .thenReturn(new BigDecimal("1800.00"));

        mockMvc.perform(post("/billing")
                        .content(mapper.writeValueAsString(sale))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(mockRes -> {
                    assertEquals("1800.00", mockRes.getResponse().getContentAsString());
                });
    }

    @SneakyThrows
    @Test
    void testUserIsAboveTwoYearsAsCustomer() {
        sale = SalesDTO.builder()
                .id(1L)
                .totalCost(new BigDecimal("2000.00"))
                .customer(Customer.builder()
                        .id(1L)
                        .firstName("Jeffrey")
                        .lastName("Adams")
                        .email("joa@gmail.com")
                        .createdAt(LocalDateTime.parse("2023-12-17T15:48:30.529059"))
                        .userType(CustomerUserType.EMPLOYEE)
                        .build())
                .product(List.of(Product.builder()
                                .id(1L)
                                .price(new BigDecimal("1000.00"))
                                .description("Product 1 Description")
                                .name("Product 1")
                                .category(Category.builder()
                                        .id(1L)
                                        .build())
                                .build(),
                        Product.builder()
                                .id(2L)
                                .price(new BigDecimal("1000.00"))
                                .description("Product 2 Description")
                                .name("Product 2")
                                .category(Category.builder()
                                        .id(2L)
                                        .build())
                                .build()))
                .build();

        when(billingService.calculateDiscount(sale))
                .thenReturn(new BigDecimal("1900.00"));

        mockMvc.perform(post("/billing")
                        .content(mapper.writeValueAsString(sale))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(mockRes -> {
                    assertEquals("1900.00", mockRes.getResponse().getContentAsString());
                });
    }
}
