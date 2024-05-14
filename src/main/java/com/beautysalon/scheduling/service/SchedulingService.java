package com.beautysalon.scheduling.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beautysalon.scheduling.model.Customer;
import com.beautysalon.scheduling.model.ProfessionalUser;
import com.beautysalon.scheduling.model.Scheduling;
import com.beautysalon.scheduling.model.TaskType;
import com.beautysalon.scheduling.model.exception.ResourceNotFoundException;
import com.beautysalon.scheduling.repository.SchedulingRepository;
import com.beautysalon.scheduling.shared.SchedulingDTO;
import com.beautysalon.scheduling.util.functions.scheduling.convertersScheduling;
import com.beautysalon.scheduling.util.validations.validModelScheduling;


@Service
public class SchedulingService{
    @Autowired
    private SchedulingRepository schedulingRepository;


    public List<SchedulingDTO> getAll(){
        List<Scheduling> schedulings = schedulingRepository.findAll();
        isEmpty(schedulings);
        List<SchedulingDTO> schedulingDTOs = convertersScheduling.convertesListSchedulingInDTOs(schedulings);
        return schedulingDTOs;
    }

    public Optional<SchedulingDTO> getById(Long id){
        existScheduling(id);
        Optional<Scheduling> schedOptional = schedulingRepository.findById(id);
        SchedulingDTO schedulingDTO = convertersScheduling.convertesSchedulingInDTO(schedOptional.get());
        return Optional.of(schedulingDTO);
    }

    public SchedulingDTO register(SchedulingDTO schedulingDTO) throws Exception{
        Scheduling scheduling = convertersScheduling.convertesDTOInScheduling(schedulingDTO);
        validModelScheduling.getInstanceSingleton()
        .existClientAndProfessionalDuplicated(schedulingDTO,findSchedulingByDateAndHoursTime(schedulingDTO));
        scheduling = schedulingRepository.save(scheduling);
        schedulingDTO.setId(scheduling.getId());
        schedulingDTO.setTotalValueTask(scheduling.getTotalValueTask());
        return schedulingDTO;
    }
    
    public List<SchedulingDTO> findAllSchedulingByIdClient(Long id){
        Customer customer = new Customer();
        customer.setId(id);
        List<SchedulingDTO> schedulingDTOs = convertersScheduling.
        convertesListSchedulingInDTOs((schedulingRepository.findByIdClient(customer)));    
        return schedulingDTOs;
    }
    public List<SchedulingDTO> findAllSchedulingByIdTask(Long id){
        TaskType taskType = new TaskType();
        taskType.setId(id);
        List<SchedulingDTO> schedulingDTOs =convertersScheduling
        .convertesListSchedulingInDTOs((schedulingRepository.findByIdTypeTask(taskType)));
        return schedulingDTOs;
    }

    public List<SchedulingDTO> findSchedulingByIdProfessUser(Long id){
        ProfessionalUser professionalUser = new ProfessionalUser();
        professionalUser.setId(id);
        List<SchedulingDTO> schedulingDTOs = convertersScheduling
        .convertesListSchedulingInDTOs((schedulingRepository.findByIdUserProfissional(professionalUser))) ;

        return schedulingDTOs;
    }
    public List<SchedulingDTO> findSchedulingByDateAndHoursTime(SchedulingDTO schedulingDTO){
        List<SchedulingDTO> schedulingDTOs = convertersScheduling
        .convertesListSchedulingInDTOs((
            schedulingRepository
            .findByDateAgendAndHorsTime(schedulingDTO.getDateAgend(),schedulingDTO.getHorsTime())
            ));
        return schedulingDTOs;
    }
    public List<SchedulingDTO> findSchedulingByDate(SchedulingDTO schedulingDTO){
        List<SchedulingDTO> schedulingDTOs = convertersScheduling
        .convertesListSchedulingInDTOs((schedulingRepository.findByDateAgend(schedulingDTO.getDateAgend())));
        return schedulingDTOs;
    }

    public void delete(Long id){
        existScheduling(id);
        schedulingRepository.deleteById(id);
    }

    public SchedulingDTO update(Long id, SchedulingDTO schedulingDTO){
        existScheduling(id);
        schedulingDTO.setId(id);
        Scheduling scheduling = convertersScheduling.convertesDTOInScheduling(schedulingDTO);
        scheduling = schedulingRepository.save(scheduling);
        schedulingDTO.setTotalValueTask(scheduling.getTotalValueTask());
        return schedulingDTO;
    }
    public void existScheduling(Long id){
        if(!this.schedulingRepository.existsById(id)){
            throw new ResourceNotFoundException("Esse Agendamento não existe!");
        }
    }
    private void isEmpty(List<Scheduling> schedulings){
        if(schedulings.isEmpty()){
            throw new ResourceNotFoundException("Nenhum Agendamento Encontrado!");
        }
    }

}
