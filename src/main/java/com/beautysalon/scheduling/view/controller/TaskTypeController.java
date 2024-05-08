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

@RestController
@RequestMapping("/restfull/v01/services")
public class TaskTypeController {
    @Autowired
    private TaskTypeService taskRepository;

    @GetMapping
    public ResponseEntity<List<TaskTypeDTO>> getAll() {
        List<TaskTypeDTO> typeTaskDTOs = taskRepository.getAll();
        return new ResponseEntity<>(typeTaskDTOs,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TaskTypeDTO> getForId(@PathVariable Long id){

        Optional<TaskTypeDTO> tOptional = taskRepository.getById(id);
        return new ResponseEntity<>(tOptional.get(),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        taskRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping
    public ResponseEntity<TaskTypeDTO> register(@RequestBody TaskTypeDTO typeTaskDTO){
        typeTaskDTO = taskRepository.register(typeTaskDTO);
        return new ResponseEntity<>(typeTaskDTO,HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<TaskTypeDTO> update(@RequestBody TaskTypeDTO typeTaskDTO,@PathVariable Long id){
        typeTaskDTO = taskRepository.update(id, typeTaskDTO);
        return new ResponseEntity<>(typeTaskDTO,HttpStatus.OK);
    }

}
