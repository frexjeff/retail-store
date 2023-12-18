package com.joa.retailstorev1.model.dto;

import com.joa.retailstorev1.model.Customer;
import com.joa.retailstorev1.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SalesDTO {

    private Long id;
    private List<Product> product;
    private Customer customer;
    private BigDecimal totalCost;
}
