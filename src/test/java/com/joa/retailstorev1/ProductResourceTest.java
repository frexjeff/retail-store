package com.joa.retailstorev1;


import com.joa.retailstorev1.model.Category;
import com.joa.retailstorev1.model.Product;
import com.joa.retailstorev1.services.ProductService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductResource.class)
class ProductResourceTest extends BaseTest {

    @MockBean
    static ProductService productService;

    Product product;


    @BeforeEach
    public void setup() {
        product = Product.builder()
                .id(4L)
                .price(new BigDecimal("2050.00"))
                .description("Product 1 Description")
                .name("Product 4")
                .category(Category.builder()
                        .id(1L)
                        .build())
                .build();
    }

    @SneakyThrows
    @Test
    void testCreateProduct() {
        when(productService.create(any(Product.class)))
                .thenReturn(product);

        mockMvc.perform(post("/product")
                        .content(mapper.writeValueAsString(product))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(mockRes -> {
                    assertEquals("{\"id\":4,\"name\":\"Product 4\",\"description\":\"Product 1 Description\",\"price\":2050.00,\"category\":{\"id\":1,\"name\":null}}", mockRes.getResponse().getContentAsString());
                });
    }

}