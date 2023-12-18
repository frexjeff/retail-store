package com.joa.retailstorev1.model.dto;

import com.joa.retailstorev1.enums.CustomerUserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CustomerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private CustomerUserType userType;
}
