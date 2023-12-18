package com.joa.retailstorev1.services.type;

import com.joa.retailstorev1.model.dto.SalesDTO;

import java.math.BigDecimal;

public interface CustomerType {
    BigDecimal calculateDiscount(SalesDTO sale);
}
