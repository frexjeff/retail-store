package com.joa.retailstorev1;


import com.joa.retailstorev1.model.Sale;
import com.joa.retailstorev1.model.dto.SalesDTO;
import com.joa.retailstorev1.services.SalesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RequestMapping("/sales")
@RestController
public class SalesResource {

    @Autowired
    SalesServices salesServices;

    @GetMapping
    public ResponseEntity<List<Sale>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(salesServices.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sale> get(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(salesServices.get(id));
    }

    @Transactional
    @PostMapping
    public ResponseEntity<SalesDTO> create(@RequestBody Sale sale) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(salesServices.create(sale));
    }
}
