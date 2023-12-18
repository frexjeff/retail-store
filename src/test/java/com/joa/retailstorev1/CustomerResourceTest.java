package com.joa.retailstorev1;


import com.joa.retailstorev1.enums.CustomerUserType;
import com.joa.retailstorev1.model.Customer;
import com.joa.retailstorev1.model.dto.CustomerDTO;
import com.joa.retailstorev1.services.CustomerService;
import lombok.SneakyThrows;
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

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@WebMvcTest(CustomerResource.class)
class CustomerResourceTest {

    @MockBean
    static CustomerService customerService;

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
//        when(customerService.create(any(CustomerDTO.class))
//                .thenReturn(customer);
//
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .post("/category")
//                .accept(MediaType.APPLICATION_JSON).content("{name\":\"Category 1\"}")
//                .contentType(MediaType.APPLICATION_JSON);
//
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//
//        MockHttpServletResponse response = result.getResponse();
//        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
//        assertEquals(response.getContentAsString(), "{\"id\":1,\"firstName\":\"Jeffrey\",\"lastName\":\"Adams\",\"email\":\"joa@gmail.com\",\"userType\":\"CUSTOMER\"}");

    }


}