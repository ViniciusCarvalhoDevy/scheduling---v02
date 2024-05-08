package com.beautysalon.scheduling.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beautysalon.scheduling.model.ProfessionalUser;
import com.beautysalon.scheduling.model.exception.ResourceBadRequestException;
import com.beautysalon.scheduling.model.exception.ResourceNotFoundException;
import com.beautysalon.scheduling.repository.ProfessionalUserRepository;
import com.beautysalon.scheduling.shared.ProfessionalUserDTO;
import com.beautysalon.scheduling.util.interfaces.instancesGlobal;
 
@Service
public class ProfessionalUserService implements instancesGlobal {
    
    @Autowired
    private ProfessionalUserRepository professionalUserRepository;

    public List<ProfessionalUserDTO> getAll(Long id_sender) throws Exception{
        validIfIsAdmin(id_sender);
        List<ProfessionalUser> professionalUsers = professionalUserRepository.findAll();

        if(professionalUsers.isEmpty()){
            throw new ResourceNotFoundException("Nenhum Funcionário Cadastrado!");
        }
        List<ProfessionalUserDTO> professionalUserDTOs = professionalUsers.stream()
        .map(prfUser -> mapper.map(prfUser,ProfessionalUserDTO.class))
        .collect(Collectors.toList());

        return professionalUserDTOs;

    }

    public Optional<ProfessionalUserDTO> getById(Long id_sender,Long id) throws Exception{
        validIfIsAdmin(id_sender);
        Optional<ProfessionalUser> prOptional = professionalUserRepository.findById(id);
        ProfessionalUserDTO professionalUserDTO =mapper.map(prOptional.get(), ProfessionalUserDTO.class);

        return Optional.of(professionalUserDTO);
        
    }
    
    public ProfessionalUserDTO register(Long id_sender, ProfessionalUserDTO professionalUserDTO) throws Exception{
        validIfIsAdmin(id_sender);
        ProfessionalUser professionalUser =mapper.map(professionalUserDTO,ProfessionalUser.class);
        professionalUser.setIsAdmin(false);
        professionalUser = professionalUserRepository.save(professionalUser);
        professionalUserDTO.setId(professionalUser.getId());
        professionalUserDTO.setIsAdmin(professionalUser.getIsAdmin());
        return professionalUserDTO;
        
    }
    public ProfessionalUserDTO registerAdmin(Long password, ProfessionalUserDTO professionalUserDTO) throws Exception{
        if(password != 8658){
            throw new ResourceBadRequestException("Senha de autorização invalida!");
        }
        ProfessionalUser professionalUser =mapper.map(professionalUserDTO,ProfessionalUser.class);
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
        ProfessionalUser professionalUser =mapper.map(professionalUserDTO, ProfessionalUser.class);
        professionalUser = professionalUserRepository.save(professionalUser);
        
        return professionalUserDTO;
        
    }

    private void validIfIsAdmin(Long id_sender) throws Exception{
        Optional<ProfessionalUser> sender = Optional.ofNullable(null);

            sender = professionalUserRepository.findById(id_sender);

        if(sender.isPresent()){
             if(!sender.get().getIsAdmin()){
                throw new ResourceBadRequestException("Esse Usuário não tem essa permissão!");
            }
        }else{
            throw new ResourceBadRequestException("Este Usuário não existe!");

        }
           

    }
    public List<ProfessionalUserDTO> findAllByIdProfessUser(List<Long> ids){
        List<ProfessionalUserDTO> dUserDTOs = (professionalUserRepository.findAllById(ids)).stream()
        .map(cl -> mapper.map(cl,ProfessionalUserDTO.class))
        .collect(Collectors.toList());
        return dUserDTOs;
    }
}
