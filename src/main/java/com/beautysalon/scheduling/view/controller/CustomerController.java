package com.beautysalon.scheduling.view.controller;

import org.springframework.web.bind.annotation.RestController;

import com.beautysalon.scheduling.service.CustomerService;
import com.beautysalon.scheduling.shared.CustomerDTO;

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
@RequestMapping("/restfull/v01/client")
public class CustomerController {
    
    @Autowired
    private CustomerService clientService;

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAll() {
        List<CustomerDTO> clientDTOs = clientService.getAll();
        return new ResponseEntity<>(clientDTOs,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getForId(@PathVariable Long id){

        Optional<CustomerDTO> clientDTO = clientService.getById(id);
        return new ResponseEntity<>(clientDTO.get(),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        clientService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping
    public ResponseEntity<CustomerDTO> register(@RequestBody CustomerDTO clientDTO){
        clientDTO = clientService.register(clientDTO);
        return new ResponseEntity<>(clientDTO,HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> update(@RequestBody CustomerDTO clientDTO,@PathVariable Long id){
        clientDTO = clientService.update(id, clientDTO);
        return new ResponseEntity<>(clientDTO,HttpStatus.OK);
    }


    
}
