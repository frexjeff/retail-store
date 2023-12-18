package com.joa.retailstorev1.services;


import com.joa.retailstorev1.model.dto.SalesDTO;
import com.joa.retailstorev1.services.type.CustomerType;
import com.joa.retailstorev1.services.type.CustomerTypeFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;


@Service
@Slf4j
public class BillingService {

    CustomerType customerType;

    @Autowired
    CustomerTypeFactory customerTypeFactory;

    public BigDecimal calculateDiscount(SalesDTO sale) {
        log.info("Sales: {}", sale);
        customerType = customerTypeFactory.getCustomerType(sale.getCustomer().getUserType().getType());
        BigDecimal value;

        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(sale.getCustomer().getCreatedAt().toLocalDate(), currentDate);
        if (period.getYears() > 2) {
            value = sale.getTotalCost().subtract(
                    sale.getTotalCost().multiply(new BigDecimal(".05")));
        } else value = customerType.calculateDiscount(sale);
        return removeFiveDollarDiscount(value);
    }

    public BigDecimal removeFiveDollarDiscount(BigDecimal amount) {
        log.info("Before dividing: {}", amount);
        if(amount.compareTo(new BigDecimal("100")) > 0) {
            BigDecimal amtA = amount.divide(new BigDecimal("100"), 0, RoundingMode.FLOOR);
            log.info("After diving by 100: {}", amtA);
            BigDecimal amtB = amtA.multiply(new BigDecimal("5"));
            log.info("multiply by 5: {}", amtB);
            amount = amount.subtract(amtB);
            log.info("subtract after multiplying by 5: {}", amount);
            return amount;
        }
        return amount;

    }

}
