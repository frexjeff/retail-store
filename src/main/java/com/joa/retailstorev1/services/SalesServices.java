package com.joa.retailstorev1.services;


import com.joa.retailstorev1.model.Sale;
import com.joa.retailstorev1.model.dto.SalesDTO;
import com.joa.retailstorev1.repository.SaleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesServices {

    private final SaleRepository saleRepository;

    public SalesServices(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public SalesDTO create(Sale sales) {
        saleRepository.save(sales);
        return SalesDTO.builder()
                .id(sales.getId())
                .customer(sales.getCustomer())
                .product(sales.getProduct())
                .totalCost(sales.getTotalCost())
                .build();
    }

    public List<Sale> getAll() {
        return saleRepository.findAll();
    }

    public Sale get(Long id) {
        return saleRepository.findById(id).get();
    }

}
