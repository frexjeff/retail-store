package com.joa.retailstorev1;

import com.joa.retailstorev1.model.dto.SalesDTO;
import com.joa.retailstorev1.services.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@RequestMapping("/billing")
@RestController
public class BillingResource {

    @Autowired
    private BillingService billingService;

    @PostMapping
    @Transactional
    public ResponseEntity<BigDecimal> findNetPayable(@RequestBody SalesDTO sale) {
        return new ResponseEntity<>(billingService.calculateDiscount(sale), HttpStatus.OK);
    }
}
