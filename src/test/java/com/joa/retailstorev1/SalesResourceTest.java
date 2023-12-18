package com.joa.retailstorev1;


import com.joa.retailstorev1.enums.CustomerUserType;
import com.joa.retailstorev1.model.Category;
import com.joa.retailstorev1.model.Customer;
import com.joa.retailstorev1.model.Product;
import com.joa.retailstorev1.model.Sale;
import com.joa.retailstorev1.model.dto.SalesDTO;
import com.joa.retailstorev1.services.SalesServices;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SalesResource.class)
class SalesResourceTest extends BaseTest {

    @MockBean
    SalesServices salesServices;

    SalesDTO sale;

    @BeforeEach
    public void setup() {
        mapper.findAndRegisterModules();


    }

    @SneakyThrows
    @Test
    void testCreateSales() {
        sale = SalesDTO.builder()
                .id(1L)
                .totalCost(new BigDecimal("2000.00"))
                .customer(Customer.builder()
                        .id(1L)
                        .firstName("Jeffrey")
                        .lastName("Adams")
                        .email("joa@gmail.com")
                        .createdAt(LocalDateTime.parse("2023-12-17T15:48:30.529059"))
                        .userType(CustomerUserType.CUSTOMER)
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

        when(salesServices.create(any(Sale.class)))
                .thenReturn(sale);

        mockMvc.perform(post("/sales")
                        .content(mapper.writeValueAsString(sale))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(mockRes -> {
                    assertEquals("{\"id\":1,\"product\":[{\"id\":1,\"name\":\"Product 1\",\"description\":\"Product 1 Description\",\"price\":1000.00,\"category\":{\"id\":1,\"name\":null}},{\"id\":2,\"name\":\"Product 2\",\"description\":\"Product 2 Description\",\"price\":1000.00,\"category\":{\"id\":2,\"name\":null}}],\"customer\":{\"id\":1,\"firstName\":\"Jeffrey\",\"lastName\":\"Adams\",\"email\":\"joa@gmail.com\",\"userType\":\"CUSTOMER\",\"createdAt\":\"2023-12-17T15:48:30.529059\"},\"totalCost\":2000.00}",
                            mockRes.getResponse().getContentAsString());
                });
    }

}