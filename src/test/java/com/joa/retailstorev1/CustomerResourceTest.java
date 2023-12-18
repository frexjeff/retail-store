package com.joa.retailstorev1;


import com.joa.retailstorev1.enums.CustomerUserType;
import com.joa.retailstorev1.model.Customer;
import com.joa.retailstorev1.model.dto.CustomerDTO;
import com.joa.retailstorev1.services.CustomerService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerResource.class)
class CustomerResourceTest extends BaseTest {


    @MockBean
    CustomerService customerService;

    CustomerDTO customer;

    @BeforeEach
    public void setup() {
        customer = CustomerDTO.builder()
                .id(1L)
                .firstName("Jeffrey")
                .lastName("Adams")
                .email("joa@gmail.com")
                .userType(CustomerUserType.CUSTOMER)
                .build();
    }

    @SneakyThrows
    @Test
    void testCreateCustomer() {
        when(customerService.create(any(Customer.class)))
                .thenReturn(customer);

        mockMvc.perform(post("/customer")
                        .content(mapper.writeValueAsString(customer))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(mockRes -> {
                    assertEquals("{\"id\":1,\"firstName\":\"Jeffrey\",\"lastName\":\"Adams\",\"email\":\"joa@gmail.com\",\"userType\":\"CUSTOMER\"}", mockRes.getResponse().getContentAsString());
                });

    }


}