package com.beautysalon.scheduling.view.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beautysalon.scheduling.service.ProfessionalUserService;
import com.beautysalon.scheduling.shared.ProfessionalUserDTO;

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
@RequestMapping("/restfull/v01/userprofess")
public class ProfessionalUserController {
    
    @Autowired
    private ProfessionalUserService professionalUserService;
    

    
    @GetMapping("/{id_sender}")
    public ResponseEntity<List<ProfessionalUserDTO>> getAll(@PathVariable Long id_sender) throws Exception {
        List<ProfessionalUserDTO> professionalUserDTOs = professionalUserService.getAll(id_sender);
        return new ResponseEntity<>(professionalUserDTOs,HttpStatus.OK);    
    }
    @GetMapping("/{id_sender}/{id}")
    public ResponseEntity<ProfessionalUserDTO> getForId(@PathVariable Long id_sender,@PathVariable Long id)throws Exception{
        Optional<ProfessionalUserDTO> prOptional = professionalUserService.getById(id_sender,id);
        return new ResponseEntity<>(prOptional.get(),HttpStatus.OK);
    }
    @DeleteMapping("/{id_sender}/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id_sender,@PathVariable Long id) throws Exception{
        professionalUserService.delete(id_sender,id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping("/{id_sender}")
    public ResponseEntity<ProfessionalUserDTO> register(@PathVariable Long id_sender,@RequestBody ProfessionalUserDTO professionalUserDTO) throws Exception{
        professionalUserDTO = professionalUserService.register(id_sender,professionalUserDTO);
        return new ResponseEntity<>(professionalUserDTO,HttpStatus.CREATED);    
    }
    @PostMapping("/admin/{password}")
    public ResponseEntity<ProfessionalUserDTO> registerAdmin(@PathVariable Long password,@RequestBody ProfessionalUserDTO professionalUserDTO) throws Exception{
        professionalUserDTO = professionalUserService.registerAdmin(password,professionalUserDTO);
        return new ResponseEntity<>(professionalUserDTO,HttpStatus.CREATED);
    }
    @PutMapping("/{id_sender}/{id}")
    public ResponseEntity<ProfessionalUserDTO> update(@PathVariable Long id_sender,@PathVariable Long id,@RequestBody ProfessionalUserDTO professionalUserDTO) throws Exception{
        professionalUserDTO = professionalUserService.update(id_sender,id,professionalUserDTO);
        return new ResponseEntity<>(professionalUserDTO,HttpStatus.OK);
    }
   
}
