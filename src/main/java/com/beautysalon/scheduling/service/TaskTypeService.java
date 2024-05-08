package com.beautysalon.scheduling.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.beautysalon.scheduling.model.TaskType;
import com.beautysalon.scheduling.model.exception.ResourceBadRequestException;
import com.beautysalon.scheduling.model.exception.ResourceNotFoundException;
import com.beautysalon.scheduling.repository.TaskTypeRepository;
import com.beautysalon.scheduling.shared.TaskTypeDTO;
import com.beautysalon.scheduling.util.interfaces.instancesGlobal;

@Service
public class TaskTypeService implements instancesGlobal {
    @Autowired
    private TaskTypeRepository taskTypeRepository;

       public List<TaskTypeDTO> getAll(){
        
        List<TaskType> typeTasks = taskTypeRepository.findAll();
        
        if(typeTasks.isEmpty()){
            throw new ResourceNotFoundException("Nenhum Serviço Encontrado!");
        }

        List<TaskTypeDTO> typeTaskDTOs = typeTasks.stream()
        .map(ttk -> mapper.map(ttk,TaskTypeDTO.class))
        .collect(Collectors.toList());

        return typeTaskDTOs;

    }
    public Optional<TaskTypeDTO> getById(Long id){
        Optional<TaskType> typeTask = taskTypeRepository.findById(id);
        TaskTypeDTO typeTaskDTO =mapper.map(typeTask.get(), TaskTypeDTO.class);

        return Optional.of(typeTaskDTO);
        
    }
    
   
    public TaskTypeDTO register(TaskTypeDTO typeTaskDTO){
        TaskType typeTask =mapper.map(typeTaskDTO,TaskType.class);
        typeTask = taskTypeRepository.save(typeTask);
        typeTaskDTO.setId(typeTask.getId());
        
        return typeTaskDTO;
    }

    public void delete(Long id){
        taskTypeRepository.deleteById(id);
    }

    public TaskTypeDTO update(Long id, TaskTypeDTO typeTaskDTO){

        if(id.getClass() != Long.class){
            throw new ResourceBadRequestException("O Id: '"+id+"' não é um numero.");
        }
        typeTaskDTO.setId(id);
        TaskType typeTask =mapper.map(typeTaskDTO, TaskType.class);
        typeTask = taskTypeRepository.save(typeTask);
        
        return typeTaskDTO;
        
    }
    public List<TaskTypeDTO> findAllByIdTask(List<Long> ids){
        List<TaskTypeDTO> dTaskDTOs= (taskTypeRepository.findAllById(ids)).stream()
        .map(cl -> mapper.map(cl,TaskTypeDTO.class))
        .collect(Collectors.toList());
        return dTaskDTOs;
    }

    
}
