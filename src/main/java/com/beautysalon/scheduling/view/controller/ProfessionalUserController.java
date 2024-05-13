package com.beautysalon.scheduling.view.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beautysalon.scheduling.service.ProfessionalUserService;
import com.beautysalon.scheduling.shared.ProfessionalUserDTO;
import com.beautysalon.scheduling.util.functions.professional.convertersProfessional;
import com.beautysalon.scheduling.view.model.ProfessionalUserHttp.ProfessionalUserRequest;
import com.beautysalon.scheduling.view.model.ProfessionalUserHttp.ProfessionalUserResponse;

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
    public ResponseEntity<List<ProfessionalUserResponse>> getAll(@PathVariable Long id_sender) throws Exception {
        List<ProfessionalUserResponse> professionalUserResponses = convertersProfessional.convertesListDTOsInResponse((professionalUserService.getAll(id_sender)));
        return new ResponseEntity<>(professionalUserResponses,HttpStatus.OK);    
    }
    @GetMapping("/{id_sender}/{id}")
    public ResponseEntity<ProfessionalUserResponse> getForId(@PathVariable Long id_sender,@PathVariable Long id)throws Exception{
        Optional<ProfessionalUserDTO> professOpt = professionalUserService.getById(id_sender,id);
        ProfessionalUserResponse professionalUserResponse = convertersProfessional
        .convertesDTOInResponseProfessionalUser(professOpt.get());
        return new ResponseEntity<>(professionalUserResponse,HttpStatus.OK);
    }
    @DeleteMapping("/{id_sender}/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id_sender,@PathVariable Long id) throws Exception{
        professionalUserService.delete(id_sender,id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping("/{id_sender}")
    public ResponseEntity<ProfessionalUserResponse> register(@PathVariable Long id_sender,@RequestBody ProfessionalUserRequest  professionalUserRequest) throws Exception{
        ProfessionalUserDTO professionalUserDTO = professionalUserService.register(
            id_sender,
            convertersProfessional.convertesRequestInDTOProfessionalUser(professionalUserRequest)
            );
        ProfessionalUserResponse professionalUserResponse = convertersProfessional.convertesDTOInResponseProfessionalUser(professionalUserDTO);
        return new ResponseEntity<>(professionalUserResponse,HttpStatus.CREATED);    
    }
    @PostMapping("/admin/{password}")
    public ResponseEntity<ProfessionalUserResponse> registerAdmin(@PathVariable Long password,@RequestBody ProfessionalUserRequest professionalUserRequest) throws Exception{
        ProfessionalUserDTO professionalUserDTO  = professionalUserService.registerAdmin(
            password,
            convertersProfessional.convertesRequestInDTOProfessionalUser(professionalUserRequest)
            );
        ProfessionalUserResponse professionalUserResponse = convertersProfessional.convertesDTOInResponseProfessionalUser(professionalUserDTO);
        return new ResponseEntity<>(professionalUserResponse,HttpStatus.CREATED);
    }

    @PutMapping("/{id_sender}/{id}")
    public ResponseEntity<ProfessionalUserResponse> update(@PathVariable Long id_sender,@PathVariable Long id,@RequestBody ProfessionalUserRequest professionalUserRequest) throws Exception{
        ProfessionalUserDTO professionalUserDTO = professionalUserService.update(
            id_sender,
            id,
            convertersProfessional.convertesRequestInDTOProfessionalUser(professionalUserRequest)
            );
        ProfessionalUserResponse professionalUserResponse = convertersProfessional.convertesDTOInResponseProfessionalUser(professionalUserDTO);
        return new ResponseEntity<>(professionalUserResponse,HttpStatus.OK);
    }
   
}
