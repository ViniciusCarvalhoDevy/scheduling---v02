package com.beautysalon.scheduling.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beautysalon.scheduling.model.ProfessionalUser;
import com.beautysalon.scheduling.model.exception.ResourceBadRequestException;
import com.beautysalon.scheduling.model.exception.ResourceNotFoundException;
import com.beautysalon.scheduling.repository.ProfessionalUserRepository;
import com.beautysalon.scheduling.shared.ProfessionalUserDTO;
import com.beautysalon.scheduling.util.functions.professional.convertersProfessional;
 
@Service
public class ProfessionalUserService{
    
    @Autowired
    private ProfessionalUserRepository professionalUserRepository;

    public List<ProfessionalUserDTO> getAll(Long id_sender) throws Exception{
        validIfIsAdmin(id_sender);
        List<ProfessionalUser> professionalUsers = professionalUserRepository.findAll();
        isEmpty(professionalUsers);
        List<ProfessionalUserDTO> professionalUserDTOs = convertersProfessional.convertesListProfessionalUserInDTOs(professionalUsers);
        return professionalUserDTOs;
    }

    public Optional<ProfessionalUserDTO> getById(Long id_sender,Long id) throws Exception{
        validIfIsAdmin(id_sender);
        Optional<ProfessionalUser> professOptional = professionalUserRepository.findById(id);
        ProfessionalUserDTO professionalUserDTO = convertersProfessional.convertesProfessionalUserInDTO(professOptional.get());
        return Optional.of(professionalUserDTO);
        
    }
    
    public ProfessionalUserDTO register(Long id_sender, ProfessionalUserDTO professionalUserDTO) throws Exception{
        validIfIsAdmin(id_sender);
        ProfessionalUser professionalUser = convertersProfessional.convertesDTOInProfessionalUser(professionalUserDTO);
        professionalUser.setIsAdmin(false);
        professionalUser = professionalUserRepository.save(professionalUser);
        professionalUserDTO.setId(professionalUser.getId());
        professionalUserDTO.setIsAdmin(professionalUser.getIsAdmin());
        return professionalUserDTO;
        
    }
    public ProfessionalUserDTO registerAdmin(Long password, ProfessionalUserDTO professionalUserDTO) throws Exception{
        isPasswordOfAdm(password);
        ProfessionalUser professionalUser = convertersProfessional.convertesDTOInProfessionalUser(professionalUserDTO);
        professionalUser.setIsAdmin(true);
        professionalUser = professionalUserRepository.save(professionalUser);
        professionalUserDTO.setId(professionalUser.getId());
        professionalUserDTO.setIsAdmin(professionalUser.getIsAdmin());
        return professionalUserDTO; 
    }
    public void delete(Long id_sender,Long id) throws Exception{
        validIfIsAdmin(id_sender);
        professionalUserRepository.deleteById(id);
    }

    public ProfessionalUserDTO update(Long id_sender,Long id, ProfessionalUserDTO professionalUserDTO) throws Exception{
        validIfIsAdmin(id_sender);
        professionalUserDTO.setId(id);
        professionalUserDTO.setIsAdmin(false);
        ProfessionalUser professionalUser = convertersProfessional.convertesDTOInProfessionalUser(professionalUserDTO);
        professionalUser = professionalUserRepository.save(professionalUser);
        return professionalUserDTO;
    }

    public List<ProfessionalUserDTO> findAllByIdProfessUser(List<Long> ids){
        List<ProfessionalUserDTO> professDTOs = convertersProfessional
        .convertesListProfessionalUserInDTOs((professionalUserRepository.findAllById(ids)));
        return professDTOs;
    }

    private void validIfIsAdmin(Long id_sender) throws Exception{
        Optional<ProfessionalUser> sender = professionalUserRepository.findById(id_sender);
        ifNotPresentOrNotAdmCallExeption(sender);
    }

    private void ifNotPresentOrNotAdmCallExeption(Optional<ProfessionalUser> professOpt ){
        if(professOpt.isPresent()){
            if(!professOpt.get().getIsAdmin()){
               throw new ResourceBadRequestException("Esse Usuário não tem essa permissão!");
               }
        }else{
           throw new ResourceBadRequestException("Este Usuário não existe!");
           }
    }

    private void isEmpty(List<ProfessionalUser> professionalUsers){
        if(professionalUsers.isEmpty()){
            throw new ResourceNotFoundException("Nenhum Funcionário Cadastrado!");
        }
    }
    private void isPasswordOfAdm(Long password){
        if(password != 8658){
            throw new ResourceBadRequestException("Senha de autorização invalida!");
        }
    }

}
