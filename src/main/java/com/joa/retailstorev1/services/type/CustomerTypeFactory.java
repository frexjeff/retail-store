package com.joa.retailstorev1.services.type;

import com.joa.retailstorev1.enums.CustomerUserType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@Slf4j
public class CustomerTypeFactory {

    @Autowired
    BeanFactory beanFactory;

    @Bean
    @Scope(value = "prototype")
    public CustomerType getCustomerType(String customerUserType) {
        log.info("customer type: {}", customerUserType);
        switch (customerUserType.toUpperCase()) {
            case "EMPLOYEE": {
                return beanFactory.getBean(Employee.class);
            }
            case "AFFILIATE": {
                return beanFactory.getBean(AffiliateCustomer.class);
            }
            default: {
                throw new NoSuchBeanDefinitionException("Bean not found");
            }
        }
    }
}
