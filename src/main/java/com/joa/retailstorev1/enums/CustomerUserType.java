package com.joa.retailstorev1.enums;

import lombok.Getter;

@Getter
public enum CustomerUserType {
    EMPLOYEE("employee"),
    AFFILIATE("affiliate"),
    CUSTOMER("customer");

    final String type;

    CustomerUserType(String type) {
        this.type = type;
    }

}
