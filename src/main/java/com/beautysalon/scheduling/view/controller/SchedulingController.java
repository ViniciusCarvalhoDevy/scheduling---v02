package com.beautysalon.scheduling.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import com.beautysalon.scheduling.shared.CustomerDTO;
import com.beautysalon.scheduling.shared.ProfessionalUserDTO;
import com.beautysalon.scheduling.shared.SchedulingDTO;
import com.beautysalon.scheduling.shared.TaskTypeDTO;
import com.beautysalon.scheduling.util.interfaces.instancesGlobal;
import com.beautysalon.scheduling.view.model.SchedulingHttp.SchedulingRequest;
import com.beautysalon.scheduling.view.model.SchedulingHttp.SchedulingResponse;
@RestController
@RequestMapping("/restfull/v01/scheduling")
public class SchedulingController implements instancesGlobal {
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
        System.err.println(schedulingDTOs);
        List<SchedulingResponse> schedulingResponses = schedulingDTOs.stream()
        .map(sch -> mapper.map(sch,SchedulingResponse.class))
        .collect(Collectors.toList());
        return new ResponseEntity<>(schedulingResponses,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<SchedulingResponse> getForId(@PathVariable Long id){

        Optional<SchedulingDTO> schOptional = schedulingService.getById(id);
        SchedulingResponse schedilingResponse = mapper.map(schOptional.get(), SchedulingResponse.class);
        return new ResponseEntity<>(schedilingResponse,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        schedulingService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping
    public ResponseEntity<SchedulingResponse> register(@Validated @RequestBody SchedulingRequest schedulingRequest){
        List<CustomerDTO> customerDTOs = customerService.findAllByIdClient(schedulingRequest.getIdClient());
        List<TaskTypeDTO> taskTypeDTOs = taskTypeService.findAllByIdTask(schedulingRequest.getIdTypeTask());
        List<ProfessionalUserDTO> professionalUserDTOs = professionalUserService.findAllByIdProfessUser(schedulingRequest.getIdUserProfissional());
        SchedulingDTO schedulingDTO = mapper.map(schedulingRequest,SchedulingDTO.class);
        
        schedulingDTO.setIdClient(
            customerDTOs
        );
        schedulingDTO.setIdTypeTask(
            taskTypeDTOs
        );
        schedulingDTO.setIdUserProfissional(
            professionalUserDTOs
        );
        schedulingDTO = schedulingService.register(schedulingDTO);
        SchedulingResponse schedilingResponse = mapper.map(schedulingDTO, SchedulingResponse.class);
        return new ResponseEntity<>(schedilingResponse,HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<SchedulingResponse> update(@Validated @RequestBody SchedulingRequest schedulingRequest,@PathVariable Long id){
        SchedulingDTO schedulingDTO = mapper.map(schedulingRequest,SchedulingDTO.class);
        schedulingDTO.setIdClient(
            (customerService.findAllByIdClient(schedulingRequest.getIdClient()))
        );
        schedulingDTO.setIdTypeTask(
            (taskTypeService.findAllByIdTask(schedulingRequest.getIdTypeTask()))
        );
        schedulingDTO.setIdUserProfissional(
            (professionalUserService.findAllByIdProfessUser(schedulingRequest.getIdUserProfissional()))
        );
        schedulingDTO = schedulingService.update(id, schedulingDTO);
        SchedulingResponse schedilingResponse = mapper.map(schedulingDTO, SchedulingResponse.class);
        return new ResponseEntity<>(schedilingResponse,HttpStatus.OK);
    }
    @GetMapping("/tasks")
    public ResponseEntity<List<TaskTypeDTO>> findSchedulingForIdsTask(@RequestBody List<Long> ids){
        List<TaskTypeDTO> taskDTO = (schedulingService.findAllSchedulingByIdTask(ids)).stream()
        .map(task -> mapper.map(task,TaskTypeDTO.class))
        .collect(Collectors.toList());
        return new ResponseEntity<>(taskDTO,HttpStatus.OK);
    }
    @GetMapping("/clients")
    public ResponseEntity<List<CustomerDTO>> findSchedulingForIdsClient(@RequestBody List<Long> ids){
        List<CustomerDTO> clientDTOs = (schedulingService.findAllSchedulingByIdClient(ids)).stream()
        .map(cl -> mapper.map(cl,CustomerDTO.class))
        .collect(Collectors.toList());
        return new ResponseEntity<>(clientDTOs,HttpStatus.OK);
    }
    @GetMapping("/profss")
    public ResponseEntity<List<ProfessionalUserDTO>> findSchedulingForIdsProfessUser(@RequestBody List<Long> ids){
        List<ProfessionalUserDTO> professionalUserDTOs = (schedulingService.findAllSchedulingByIdProfessUser(ids)).stream()
        .map(proUser -> mapper.map(proUser,ProfessionalUserDTO.class))
        .collect(Collectors.toList());
        return new ResponseEntity<>(professionalUserDTOs,HttpStatus.OK);
    }
}
