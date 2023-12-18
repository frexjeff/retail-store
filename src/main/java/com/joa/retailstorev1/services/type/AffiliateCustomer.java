package com.joa.retailstorev1.services.type;


import com.joa.retailstorev1.model.dto.SalesDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Objects;

@Component
public class AffiliateCustomer implements CustomerType {

    @Override
    public BigDecimal calculateDiscount(SalesDTO sale) {
        return sale.getTotalCost().subtract(sale
                .getProduct()
                .stream()
                .filter(prod -> !Objects.equals(prod.getCategory().getName().toLowerCase(), "grocery"))
                .map(prod -> prod.getPrice().multiply(new BigDecimal(".10")))
                .reduce(BigDecimal.ZERO, BigDecimal::add));
    }
}
