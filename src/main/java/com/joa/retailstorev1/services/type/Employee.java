package com.joa.retailstorev1.services.type;

import com.joa.retailstorev1.model.dto.SalesDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Slf4j
public class Employee implements CustomerType {
    @PostConstruct
    public void init() {
        log.info("Customer type: {}", this);
    }

    @Override
    public BigDecimal calculateDiscount(SalesDTO sale) {
        return sale.getTotalCost().subtract(sale
                .getProduct()
                .stream()
                .filter(prod -> !Objects.equals(prod.getCategory().getName().toLowerCase(), "grocery"))
                .map(prod -> prod.getPrice().multiply(new BigDecimal(".30")))
                .reduce(BigDecimal.ZERO, BigDecimal::add));
    }
}
