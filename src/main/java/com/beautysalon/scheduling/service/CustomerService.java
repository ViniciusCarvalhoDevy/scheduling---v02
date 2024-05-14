package com.beautysalon.scheduling.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beautysalon.scheduling.model.Customer;
import com.beautysalon.scheduling.model.exception.ResourceNotFoundException;
import com.beautysalon.scheduling.repository.CustomerRepository;
import com.beautysalon.scheduling.shared.CustomerDTO;
import com.beautysalon.scheduling.util.functions.customer.convertersCustomer;
@Service
public class CustomerService{

    @Autowired
    private CustomerRepository customerRepository;
    
    public List<CustomerDTO> getAll(){
        List<Customer> customers = customerRepository.findAll();
        isEmpty(customers);
        List<CustomerDTO> customerDTOs = convertersCustomer.convertesListCustomerInDTOs(customers);
        return customerDTOs;
    }
    public Optional<CustomerDTO> getById(Long id) throws Exception{
        existCustomer(id);
        Optional<Customer> customerOpt = customerRepository.findById(id);
        CustomerDTO customerDTO = convertersCustomer.convertesCustomerInDTO(customerOpt.get());
        return Optional.of(customerDTO);
    }
    
    public CustomerDTO register(CustomerDTO customerDTO){
        Customer customer = convertersCustomer.convertesDTOInCustomer(customerDTO);
        customer = customerRepository.save(customer);
        customerDTO.setId(customer.getId());
        return customerDTO;   
    }

    public void delete(Long id) throws Exception{
        existCustomer(id);
        customerRepository.deleteById(id);
    }

    public CustomerDTO update(Long id, CustomerDTO customerDTO) throws Exception{
        existCustomer(id);
        customerDTO.setId(id);
        Customer customer = convertersCustomer.convertesDTOInCustomer(customerDTO);
        customer = customerRepository.save(customer);
        return customerDTO;
    }
    public List<CustomerDTO> findAllByIdClient(List<Long> ids){
        List<CustomerDTO> dClientDTOs = convertersCustomer.convertesListCustomerInDTOs((customerRepository.findAllById(ids)));
        return dClientDTOs;
    }
    private void isEmpty(List<Customer> customers){
        if(customers.isEmpty()){
            throw new ResourceNotFoundException("Nenhum Cliente Encontrado!");
        }
    }
    private void existCustomer(Long id) throws Exception{
        if(!this.customerRepository.existsById(id)){
            throw new ResourceNotFoundException("Nenhum Cliente Encontrado com esse Id!");
        }
    }

   
    }




