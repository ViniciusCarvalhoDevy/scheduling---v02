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

import com.beautysalon.scheduling.service.TypeTaskService;
import com.beautysalon.scheduling.shared.TypeTaskDTO;

@RestController
@RequestMapping("/restfull/v01/services")
public class TypeTaskController {
    @Autowired
    private TypeTaskService taskRepository;

    @GetMapping
    public ResponseEntity<List<TypeTaskDTO>> getAll() {
        List<TypeTaskDTO> typeTaskDTOs = taskRepository.getAll();
        return new ResponseEntity<>(typeTaskDTOs,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TypeTaskDTO> getForId(@PathVariable Long id){

        Optional<TypeTaskDTO> tOptional = taskRepository.getById(id);
        return new ResponseEntity<>(tOptional.get(),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        taskRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping
    public ResponseEntity<TypeTaskDTO> register(@RequestBody TypeTaskDTO typeTaskDTO){
        typeTaskDTO = taskRepository.register(typeTaskDTO);
        return new ResponseEntity<>(typeTaskDTO,HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<TypeTaskDTO> update(@RequestBody TypeTaskDTO typeTaskDTO,@PathVariable Long id){
        typeTaskDTO = taskRepository.update(id, typeTaskDTO);
        return new ResponseEntity<>(typeTaskDTO,HttpStatus.OK);
    }

}
