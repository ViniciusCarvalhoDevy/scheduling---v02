package com.beautysalon.scheduling.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beautysalon.scheduling.model.TaskType;
import com.beautysalon.scheduling.model.exception.ResourceNotFoundException;
import com.beautysalon.scheduling.repository.TaskTypeRepository;
import com.beautysalon.scheduling.shared.TaskTypeDTO;
import com.beautysalon.scheduling.util.functions.task.convertersTask;

@Service
public class TaskTypeService{
    @Autowired
    private TaskTypeRepository taskTypeRepository;

    public List<TaskTypeDTO> getAll(){
        List<TaskType> taskTypes = taskTypeRepository.findAll();
        isEmpty(taskTypes);
        List<TaskTypeDTO> typeTaskDTOs = convertersTask.convertesListTaskTypeInDTOs(taskTypes);
        return typeTaskDTOs;
    }
    public Optional<TaskTypeDTO> getById(Long id)throws Exception{
        existTask(id);
        Optional<TaskType> taskOpt = taskTypeRepository.findById(id);
        TaskTypeDTO taskTypeDTO = convertersTask.convertesTaskTypeInDTO(taskOpt.get());
        return Optional.of(taskTypeDTO);  
    }
   
    public TaskTypeDTO register(TaskTypeDTO taskTypeDTO){
        TaskType taskType = convertersTask.convertesDTOInTaskType(taskTypeDTO);
        taskType = taskTypeRepository.save(taskType);
        taskTypeDTO.setId(taskType.getId());
        return taskTypeDTO;
    }

    public void delete(Long id)throws Exception{
        existTask(id);
        taskTypeRepository.deleteById(id);
    }

    public TaskTypeDTO update(Long id, TaskTypeDTO taskTypeDTO)throws Exception{
        existTask(id);
        taskTypeDTO.setId(id);
        TaskType taskType = convertersTask.convertesDTOInTaskType(taskTypeDTO);
        taskType = taskTypeRepository.save(taskType);
        return taskTypeDTO;
    }

    public List<TaskTypeDTO> findAllByIdTask(List<Long> ids){
        List<TaskTypeDTO> taskTypeDTOs = convertersTask.convertesListTaskTypeInDTOs((taskTypeRepository.findAllById(ids))); 
        return taskTypeDTOs;
    }
    private void isEmpty(List<TaskType> taskTypes){
        if(taskTypes.isEmpty()){
            throw new ResourceNotFoundException("Nenhum Serviço Encontrado!");
        }
    }
    private void existTask(Long id) throws Exception{
        if(!this.taskTypeRepository.existsById(id)){
            throw new ResourceNotFoundException("Nenhum Serviço Encontrado com esse ID!");
        }
    }

    
}
