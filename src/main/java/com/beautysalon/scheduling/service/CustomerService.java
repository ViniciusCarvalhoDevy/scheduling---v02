package com.beautysalon.scheduling.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beautysalon.scheduling.model.Customer;
import com.beautysalon.scheduling.model.exception.ResourceNotFoundException;
import com.beautysalon.scheduling.repository.CustomerRepository;
import com.beautysalon.scheduling.shared.CustomerDTO;
import com.beautysalon.scheduling.util.interfaces.instancesGlobal;

@Service
public class CustomerService implements instancesGlobal{

    @Autowired
    private CustomerRepository customerRepository;
    
    public List<CustomerDTO> getAll(){
        
        List<Customer> client = customerRepository.findAll();
        
        if(client.isEmpty()){
            throw new ResourceNotFoundException("Nenhum Cliente Encontrado!");
        }
        List<CustomerDTO> clientDTOs = client.stream()
        .map(cl -> mapper.map(cl,CustomerDTO.class))
        .collect(Collectors.toList());
     
        return clientDTOs;

    }
    public Optional<CustomerDTO> getById(Long id){

        Optional<Customer> client = customerRepository.findById(id);
        CustomerDTO clientDTO =mapper.map(client.get(), CustomerDTO.class);

        return Optional.of(clientDTO);
        
    }
    
    public CustomerDTO register(CustomerDTO clientDTO){
        
        Customer client = mapper.map(clientDTO,Customer.class);
        client = customerRepository.save(client);
        clientDTO.setId(client.getId());
        return clientDTO;
        
    }
    public void delete(Long id){
        customerRepository.deleteById(id);
    }

    public CustomerDTO update(Long id, CustomerDTO clientDTO){

        clientDTO.setId(id);
        Customer client =mapper.map(clientDTO, Customer.class);
        client = customerRepository.save(client);
        
        return clientDTO;
        
    }
    public List<CustomerDTO> findAllByIdClient(List<Long> ids){
        List<CustomerDTO> dClientDTOs = (customerRepository.findAllById(ids)).stream()
        .map(cl -> mapper.map(cl,CustomerDTO.class))
        .collect(Collectors.toList());
        return dClientDTOs;
    }
    }




