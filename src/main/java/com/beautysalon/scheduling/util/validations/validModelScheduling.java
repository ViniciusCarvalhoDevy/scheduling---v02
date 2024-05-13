package com.beautysalon.scheduling.util.validations;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


import com.beautysalon.scheduling.model.exception.ResourceBadRequestException;
import com.beautysalon.scheduling.shared.CustomerDTO;
import com.beautysalon.scheduling.shared.ProfessionalUserDTO;
import com.beautysalon.scheduling.shared.SchedulingDTO;
import com.beautysalon.scheduling.util.interfaces.instancesGlobal;

public class validModelScheduling implements instancesGlobal {
    private static validModelScheduling validation;
    private List<Long> idsFoundCustomer = new ArrayList<>();
    private List<Long> idsFoundProfess = new ArrayList<>();

    private validModelScheduling() {}

    public static validModelScheduling getInstanceSingleton(){
      if(validation == null){
        validation = new validModelScheduling();
      }
      return  validation;
    }
    public void existClientAndProfessionalDuplicated(SchedulingDTO schedulingDTORequest,List<SchedulingDTO> schedulingDTOsConsultForDateAndHours) throws Exception{
      
      for(CustomerDTO customerDTO : schedulingDTORequest.getIdClient()){
        this.idsFoundCustomer = (schedulingDTOsConsultForDateAndHours.stream()
        .map(cl -> cl.getIdClient().contains(customerDTO) ? customerDTO.getId() : 0).collect(Collectors.toList())); 
      }
      for(ProfessionalUserDTO professionalUserDTO : schedulingDTORequest.getIdUserProfissional()){
        this.idsFoundProfess = (schedulingDTOsConsultForDateAndHours.stream().map(profss -> profss.getIdUserProfissional().contains(professionalUserDTO) ? professionalUserDTO.getId() : 0).collect(Collectors.toList())); 
      }
      
      ifIdsEntityFoundCallException();

    }

    public void ifIdsEntityFoundCallException() throws Exception{
      this.idsFoundCustomer.addAll(this.idsFoundProfess);
      for(Long id:this.idsFoundCustomer){
        if(id != 0){
          throw new ResourceBadRequestException("Não é possivel fazer dois agendamentos no mesmo dia e horário com os mesmo Cliente ou Profissional.");
        }
      }
    }

}
