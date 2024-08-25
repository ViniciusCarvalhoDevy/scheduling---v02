package com.beautysalon.scheduling.util.functions.professional;

import java.util.List;
import java.util.stream.Collectors;

import com.beautysalon.scheduling.model.ProfessionalUser;
import com.beautysalon.scheduling.shared.ProfessionalUserDTO;
import com.beautysalon.scheduling.util.interfaces.instancesGlobal;
import com.beautysalon.scheduling.view.model.ProfessionalUserHttp.ProfessionalUserRequest;
import com.beautysalon.scheduling.view.model.ProfessionalUserHttp.ProfessionalUserResponse;


public class convertersProfessional implements instancesGlobal {
    private convertersProfessional() {}

     public static ProfessionalUserDTO convertesRequestInDTOProfessionalUser(ProfessionalUserRequest request){
        ProfessionalUserDTO professionalUserDTO =  mapper.map(request,ProfessionalUserDTO.class);
        return professionalUserDTO;
    }
    public static ProfessionalUserResponse convertesDTOInResponseProfessionalUser(ProfessionalUserDTO dto){
        ProfessionalUserResponse ProfessionalUserResponse =  mapper.map(dto,ProfessionalUserResponse.class);
        return ProfessionalUserResponse;
    }
    public static ProfessionalUserDTO convertesProfessionalUserInDTO(ProfessionalUser professionalUser){
        ProfessionalUserDTO professionalUserDTO =  mapper.map(professionalUser,ProfessionalUserDTO.class);
        return professionalUserDTO;
    }
    public static ProfessionalUser convertesDTOInProfessionalUser(ProfessionalUserDTO dto){
        ProfessionalUser ProfessionalUser =  mapper.map(dto,ProfessionalUser.class);
        return ProfessionalUser;
    }
    public static List<ProfessionalUserDTO> convertesListProfessionalUserInDTOs(List<ProfessionalUser> professionalUsers){
        List<ProfessionalUserDTO> professionalUserDTOs = professionalUsers.stream()
        .map(profess -> convertesProfessionalUserInDTO(profess))
        .collect(Collectors.toList());
        return professionalUserDTOs;
    }
    public static List<ProfessionalUserResponse> convertesListDTOsInResponse(List<ProfessionalUserDTO> professionalUsers){
        List<ProfessionalUserResponse> professionalUserResponses = professionalUsers.stream()
        .map(profess -> convertesDTOInResponseProfessionalUser(profess))
        .collect(Collectors.toList());
        return professionalUserResponses;
    }

    


}
