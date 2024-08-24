package com.beautysalon.scheduling.view.controller;

import org.springframework.web.bind.annotation.RestController;

import com.beautysalon.scheduling.service.CustomerService;
import com.beautysalon.scheduling.shared.CustomerDTO;
import com.beautysalon.scheduling.util.functions.customer.convertersCustomer;
import com.beautysalon.scheduling.view.model.CustomerHttp.CustomerRequest;
import com.beautysalon.scheduling.view.model.CustomerHttp.CustomerResponse;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/restfull/v01/customer")
public class CustomerController {
    
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAll() {
        List<CustomerResponse> customerResponses = convertersCustomer.convertesListDTOsInResponse((customerService.getAll()));
        return new ResponseEntity<>(customerResponses,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getForId(@PathVariable Long id) throws Exception{
        Optional<CustomerDTO> custumerDTO = customerService.getById(id);
        CustomerResponse customerResponse = convertersCustomer.convertesDTOInResponseCustomer(custumerDTO.get());
        return new ResponseEntity<>(customerResponse,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws Exception{
        customerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> register(@RequestBody CustomerRequest customerRequest){
        CustomerDTO customerDTO = customerService.register(
            convertersCustomer.convertesRequestInDTOCustomer(customerRequest)
        );
        CustomerResponse customerResponse = convertersCustomer.convertesDTOInResponseCustomer(customerDTO);
        return new ResponseEntity<>(customerResponse,HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> update(@RequestBody CustomerRequest customerRequest,@PathVariable Long id) throws Exception{
        CustomerDTO customerDTO = customerService.update(
            id, 
            convertersCustomer.convertesRequestInDTOCustomer(customerRequest)
        );
        CustomerResponse customerResponse = convertersCustomer.convertesDTOInResponseCustomer(customerDTO);
        return new ResponseEntity<>(customerResponse,HttpStatus.CREATED);
    }
    


    
}
