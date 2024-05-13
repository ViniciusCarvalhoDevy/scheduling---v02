package com.beautysalon.scheduling.view.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beautysalon.scheduling.service.CustomerService;
import com.beautysalon.scheduling.service.ProfessionalUserService;
import com.beautysalon.scheduling.service.SchedulingService;
import com.beautysalon.scheduling.service.TaskTypeService;
import com.beautysalon.scheduling.shared.SchedulingDTO;
import com.beautysalon.scheduling.util.functions.scheduling.convertersScheduling;
import com.beautysalon.scheduling.view.model.SchedulingHttp.SchedulingRequest;
import com.beautysalon.scheduling.view.model.SchedulingHttp.SchedulingResponse;
@RestController
@RequestMapping("/restfull/v01/scheduling")
public class SchedulingController{
     @Autowired
    private SchedulingService schedulingService;
     @Autowired
    private CustomerService customerService;
     @Autowired
    private TaskTypeService taskTypeService;
     @Autowired
    private ProfessionalUserService professionalUserService;

    @GetMapping("/all")
    public ResponseEntity<List<SchedulingResponse>> getAll() {
        List<SchedulingDTO> schedulingDTOs = schedulingService.getAll();
        List<SchedulingResponse> schedulingResponses = convertersScheduling.convertesListDTOsInResponse(schedulingDTOs);
        return new ResponseEntity<>(schedulingResponses,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<SchedulingResponse> getForId(@PathVariable Long id){
        Optional<SchedulingDTO> schedOptional = schedulingService.getById(id);
        SchedulingResponse schedilingResponse = convertersScheduling.convertesDTOInResponseScheduling(schedOptional.get());
        return new ResponseEntity<>(schedilingResponse,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        schedulingService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping
    public ResponseEntity<SchedulingResponse> register(@Validated @RequestBody SchedulingRequest schedulingRequest) throws Exception{
        SchedulingDTO schedulingDTO = convertersScheduling.convertesRequestInDTOScheduling(schedulingRequest);
        setListOfCustomerInScheduling(customerService, schedulingDTO, schedulingRequest);
        setListOfTaskInScheduling(taskTypeService, schedulingDTO, schedulingRequest);
        setListOfProfessionalInScheduling(professionalUserService, schedulingDTO, schedulingRequest);
        schedulingDTO = schedulingService.register(schedulingDTO);
        SchedulingResponse schedilingResponse = convertersScheduling.convertesDTOInResponseScheduling(schedulingDTO);
        return new ResponseEntity<>(schedilingResponse,HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<SchedulingResponse> update(@Validated @RequestBody SchedulingRequest schedulingRequest,@PathVariable Long id){
        SchedulingDTO schedulingDTO = convertersScheduling.convertesRequestInDTOScheduling(schedulingRequest);
        setListOfCustomerInScheduling(customerService, schedulingDTO, schedulingRequest);
        setListOfTaskInScheduling(taskTypeService, schedulingDTO, schedulingRequest);
        setListOfProfessionalInScheduling(professionalUserService, schedulingDTO, schedulingRequest);
        schedulingDTO = schedulingService.update(id, schedulingDTO);
        SchedulingResponse schedilingResponse = convertersScheduling.convertesDTOInResponseScheduling(schedulingDTO);
        return new ResponseEntity<>(schedilingResponse,HttpStatus.OK);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<List<SchedulingResponse>> findSchedulingForIdsTask(@PathVariable Long id){
        List<SchedulingResponse> schedulingResponses = convertersScheduling
        .convertesListDTOsInResponse((schedulingService.findAllSchedulingByIdTask(id)));
        return new ResponseEntity<>(schedulingResponses,HttpStatus.OK);
    }
    @GetMapping("/clients/{id}")
    public ResponseEntity<List<SchedulingResponse>> findSchedulingForIdsClient(@PathVariable Long id){
        List<SchedulingResponse> schedulingResponses = convertersScheduling
        .convertesListDTOsInResponse((schedulingService.findAllSchedulingByIdClient(id)));
        return new ResponseEntity<>(schedulingResponses,HttpStatus.OK);
    }

    @GetMapping("/profss/{id}")
    public ResponseEntity<List<SchedulingResponse>> findSchedulingForIdsProfessUser(@PathVariable Long id){
        List<SchedulingResponse> schedulingResponses = convertersScheduling
        .convertesListDTOsInResponse((schedulingService.findSchedulingByIdProfessUser(id)));
        return new ResponseEntity<>(schedulingResponses,HttpStatus.OK);
    }

    private void setListOfCustomerInScheduling(
        CustomerService customerService, 
        SchedulingDTO schedulingDTO, 
        SchedulingRequest schedulingRequest
        ){
        schedulingDTO.setIdClient(
            (customerService.findAllByIdClient(schedulingRequest.getIdClient()))
        );
    }
    private void setListOfTaskInScheduling(
        TaskTypeService taskTypeService, 
        SchedulingDTO schedulingDTO, 
        SchedulingRequest schedulingRequest
        ){
            schedulingDTO.setIdTypeTask(
                (taskTypeService.findAllByIdTask(schedulingRequest.getIdTypeTask()))
            );
    }
    private void setListOfProfessionalInScheduling(
        ProfessionalUserService professionalUserService, 
        SchedulingDTO schedulingDTO, 
        SchedulingRequest schedulingRequest
        ){
            schedulingDTO.setIdUserProfissional(
                (professionalUserService.findAllByIdProfessUser(schedulingRequest.getIdUserProfissional()))
            );
    }
}
