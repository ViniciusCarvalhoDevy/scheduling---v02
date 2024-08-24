package com.beautysalon.scheduling.util.functions.customer;

import java.util.List;
import java.util.stream.Collectors;

import com.beautysalon.scheduling.model.Customer;
import com.beautysalon.scheduling.shared.CustomerDTO;

import com.beautysalon.scheduling.util.interfaces.instancesGlobal;

import com.beautysalon.scheduling.view.model.CustomerHttp.CustomerRequest;
import com.beautysalon.scheduling.view.model.CustomerHttp.CustomerResponse;


public class convertersCustomer implements instancesGlobal {
    private convertersCustomer() {}

    public static CustomerDTO convertesRequestInDTOCustomer(CustomerRequest request){
        CustomerDTO customerDTO =   mapper.map(request,CustomerDTO.class);
        return customerDTO;
    }
    public static CustomerResponse convertesDTOInResponseCustomer(CustomerDTO dto){
        CustomerResponse customerResponse =   mapper.map(dto,CustomerResponse.class);
        return customerResponse;
    }
    public static CustomerDTO convertesCustomerInDTO(Customer customer){
        CustomerDTO customerDTO =  mapper.map(customer,CustomerDTO.class);
        return customerDTO;
    }
    public static Customer convertesDTOInCustomer(CustomerDTO dto){
        Customer customer =  mapper.map(dto,Customer.class);
        return customer;
    }
    public static List<CustomerDTO> convertesListCustomerInDTOs(List<Customer> customers){
        List<CustomerDTO> customerDTOs = customers.stream()
        .map(cl -> convertesCustomerInDTO(cl))
        .collect(Collectors.toList());
        return customerDTOs;
    }
    public static List<CustomerResponse> convertesListDTOsInResponse(List<CustomerDTO> customers){
        List<CustomerResponse> customerResponses = customers.stream()
        .map(cl -> convertesDTOInResponseCustomer(cl))
        .collect(Collectors.toList());
        return customerResponses;
    }




    


}
