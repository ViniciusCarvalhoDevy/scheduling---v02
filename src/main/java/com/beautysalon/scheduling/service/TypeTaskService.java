package com.beautysalon.scheduling.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.beautysalon.scheduling.model.TypeTask;
import com.beautysalon.scheduling.model.exception.ResourceBadRequestException;
import com.beautysalon.scheduling.model.exception.ResourceNotFoundException;
import com.beautysalon.scheduling.repository.TypeTaskRepository;
import com.beautysalon.scheduling.shared.TypeTaskDTO;
import com.beautysalon.scheduling.util.interfaces.instancesGlobal;

@Service
public class TypeTaskService implements instancesGlobal {
    @Autowired
    private TypeTaskRepository typeTaskRepository;

       public List<TypeTaskDTO> getAll(){
        
        List<TypeTask> typeTasks = typeTaskRepository.findAll();
        
        if(typeTasks.isEmpty()){
            throw new ResourceNotFoundException("Nenhum Serviço Encontrado!");
        }

        List<TypeTaskDTO> typeTaskDTOs = typeTasks.stream()
        .map(ttk -> mapper.map(ttk,TypeTaskDTO.class))
        .collect(Collectors.toList());

        return typeTaskDTOs;

    }
    public Optional<TypeTaskDTO> getById(Long id){
        Optional<TypeTask> typeTask = typeTaskRepository.findById(id);
        TypeTaskDTO typeTaskDTO =mapper.map(typeTask.get(), TypeTaskDTO.class);

        return Optional.of(typeTaskDTO);
        
    }
    
   
    public TypeTaskDTO register(TypeTaskDTO typeTaskDTO){
        TypeTask typeTask =mapper.map(typeTaskDTO,TypeTask.class);
        typeTask = typeTaskRepository.save(typeTask);
        typeTaskDTO.setId(typeTask.getId());
        
        return typeTaskDTO;
    }

    public void delete(Long id){
        typeTaskRepository.deleteById(id);
    }

    public TypeTaskDTO update(Long id, TypeTaskDTO typeTaskDTO){

        if(id.getClass() != Long.class){
            throw new ResourceBadRequestException("O Id: '"+id+"' não é um numero.");
        }
        typeTaskDTO.setId(id);
        TypeTask typeTask =mapper.map(typeTaskDTO, TypeTask.class);
        typeTask = typeTaskRepository.save(typeTask);
        
        return typeTaskDTO;
        
    }

    
}
