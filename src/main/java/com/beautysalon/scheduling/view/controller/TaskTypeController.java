package com.beautysalon.scheduling.view.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beautysalon.scheduling.service.TaskTypeService;
import com.beautysalon.scheduling.shared.TaskTypeDTO;
import com.beautysalon.scheduling.util.functions.task.convertersTask;
import com.beautysalon.scheduling.view.model.TaskTypeHttp.TaskTypeRequest;
import com.beautysalon.scheduling.view.model.TaskTypeHttp.TaskTypeResponse;

@RestController
@RequestMapping("/restfull/v01/services")
public class TaskTypeController{
    @Autowired
    private TaskTypeService taskRepository;

    @GetMapping
    public ResponseEntity<List<TaskTypeResponse>> getAll() {
        List<TaskTypeDTO> typeTaskDTOs = taskRepository.getAll();
        List<TaskTypeResponse> taskTypeResponse = convertersTask.convertesListDTOsInResponse(typeTaskDTOs);
        return new ResponseEntity<>(taskTypeResponse,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskTypeResponse> getForId(@PathVariable Long id) throws Exception{
        Optional<TaskTypeDTO> taskOpt = taskRepository.getById(id);
        TaskTypeResponse taskTypeResponse = convertersTask.convertesDTOInResponseTaskType(taskOpt.get());
        return new ResponseEntity<>(taskTypeResponse,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)throws Exception{
        taskRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<TaskTypeResponse> register(@RequestBody TaskTypeRequest taskTypeRequest){
        TaskTypeDTO typeTaskDTO = taskRepository.register(
            convertersTask.convertesRequestInDTOTaskType(taskTypeRequest)
        );
        TaskTypeResponse taskTypeResponse = convertersTask.convertesDTOInResponseTaskType(typeTaskDTO);
        return new ResponseEntity<>(taskTypeResponse,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskTypeResponse> update(@RequestBody TaskTypeRequest taskTypeRequest,@PathVariable Long id)throws Exception{
        TaskTypeDTO taskTypeDTO = taskRepository.update(
            id, 
           convertersTask.convertesRequestInDTOTaskType(taskTypeRequest)
            );
        TaskTypeResponse taskTypeResponse = convertersTask.convertesDTOInResponseTaskType(taskTypeDTO);
        return new ResponseEntity<>(taskTypeResponse,HttpStatus.OK);
    }

}
