package com.beautysalon.scheduling.functions;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.beautysalon.scheduling.shared.CustomerDTO;
import com.beautysalon.scheduling.util.functions.customer.convertersCustomer;
import com.beautysalon.scheduling.view.model.CustomerHttp.CustomerRequest;
import com.beautysalon.scheduling.view.model.CustomerHttp.CustomerResponse;

@SpringBootTest
public class convertersCustomerTests {
    private CustomerDTO customerDTO;
    private CustomerRequest customerRequest;
    private CustomerResponse customerResponse;
    
    @Test
    void testconvertesRequestInDTOCustomer(){
        CustomerRequest customerRequest = new CustomerRequest();
        when(convertersCustomer.convertesRequestInDTOCustomer(customerRequest)).thenReturn(customerDTO);
    }
}
