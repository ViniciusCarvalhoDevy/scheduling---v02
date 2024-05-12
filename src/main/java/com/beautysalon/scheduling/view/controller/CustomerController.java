package com.beautysalon.scheduling.view.controller;

import org.springframework.web.bind.annotation.RestController;

import com.beautysalon.scheduling.service.CustomerService;
import com.beautysalon.scheduling.shared.CustomerDTO;
import com.beautysalon.scheduling.util.interfaces.instancesGlobal;
import com.beautysalon.scheduling.view.model.Customer.CustomerResponse;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
@RequestMapping("/restfull/v01/client")
public class CustomerController implements instancesGlobal {
    
    @Autowired
    private CustomerService clientService;

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAll() {
        List<CustomerResponse> customerResponses = (clientService.getAll()).stream()
        .map(cust -> mapper.map(cust, CustomerResponse.class))
        .collect(Collectors.toList());
        return new ResponseEntity<>(customerResponses,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getForId(@PathVariable Long id){

        Optional<CustomerDTO> custumerDTO = clientService.getById(id);
        CustomerResponse customerResponse = mapper.map(custumerDTO.get(), CustomerResponse.class);
        return new ResponseEntity<>(customerResponse,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        clientService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping
    public ResponseEntity<CustomerResponse> register(@RequestBody CustomerDTO clientDTO){
        clientDTO = clientService.register(clientDTO);
        CustomerResponse customerResponse = mapper.map(clientDTO, CustomerResponse.class);
        return new ResponseEntity<>(customerResponse,HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> update(@RequestBody CustomerDTO clientDTO,@PathVariable Long id){
        clientDTO = clientService.update(id, clientDTO);
        CustomerResponse customerResponse = mapper.map(clientDTO, CustomerResponse.class);
        return new ResponseEntity<>(customerResponse,HttpStatus.CREATED);
    }


    
}
