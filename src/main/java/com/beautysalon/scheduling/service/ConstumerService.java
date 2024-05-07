package com.beautysalon.scheduling.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beautysalon.scheduling.model.Constumer;
import com.beautysalon.scheduling.model.exception.ResourceNotFoundException;
import com.beautysalon.scheduling.repository.CostumerRepository;
import com.beautysalon.scheduling.shared.ConstumerDTO;
import com.beautysalon.scheduling.util.interfaces.instancesGlobal;

@Service
public class ConstumerService implements instancesGlobal{

    @Autowired
    private CostumerRepository clientRepository;
    
    public List<ConstumerDTO> getAll(){
        
        List<Constumer> client = clientRepository.findAll();
        
        if(client.isEmpty()){
            throw new ResourceNotFoundException("Nenhum Cliente Encontrado!");
        }
        List<ConstumerDTO> clientDTOs = client.stream()
        .map(cl -> mapper.map(cl,ConstumerDTO.class))
        .collect(Collectors.toList());
     
        return clientDTOs;

    }
    public Optional<ConstumerDTO> getById(Long id){

        Optional<Constumer> client = clientRepository.findById(id);
        ConstumerDTO clientDTO =mapper.map(client.get(), ConstumerDTO.class);

        return Optional.of(clientDTO);
        
    }
    
    public ConstumerDTO register(ConstumerDTO clientDTO){
        
        Constumer client = mapper.map(clientDTO,Constumer.class);
        client = clientRepository.save(client);
        clientDTO.setId(client.getId());
        return clientDTO;
        
    }
    public void delete(Long id){
        clientRepository.deleteById(id);
    }

    public ConstumerDTO update(Long id, ConstumerDTO clientDTO){

        clientDTO.setId(id);
        Constumer client =mapper.map(clientDTO, Constumer.class);
        client = clientRepository.save(client);
        
        return clientDTO;
        
    }
    }




