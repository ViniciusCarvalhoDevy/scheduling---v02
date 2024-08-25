package com.beautysalon.scheduling.util.functions.scheduling;

import java.util.List;
import java.util.stream.Collectors;

import com.beautysalon.scheduling.model.Scheduling;
import com.beautysalon.scheduling.shared.SchedulingDTO;
import com.beautysalon.scheduling.util.interfaces.instancesGlobal;
import com.beautysalon.scheduling.view.model.SchedulingHttp.SchedulingRequest;
import com.beautysalon.scheduling.view.model.SchedulingHttp.SchedulingResponse;

public class convertersScheduling implements instancesGlobal {
    private convertersScheduling() {}

    
     public static SchedulingDTO convertesRequestInDTOScheduling(SchedulingRequest request){
        SchedulingDTO schedulingDTO =  mapper.map(request,SchedulingDTO.class);
        return schedulingDTO;
    }
    public static SchedulingResponse convertesDTOInResponseScheduling(SchedulingDTO dto){
        SchedulingResponse SchedulingResponse =  mapper.map(dto,SchedulingResponse.class);
        return SchedulingResponse;
    }
    public static SchedulingDTO convertesSchedulingInDTO(Scheduling scheduling){
        SchedulingDTO schedulingDTO =  mapper.map(scheduling,SchedulingDTO.class);
        return schedulingDTO;
    }
    public static Scheduling convertesDTOInScheduling(SchedulingDTO dto){
        Scheduling Scheduling =  mapper.map(dto,Scheduling.class);
        return Scheduling;
    }
    public static List<SchedulingDTO> convertesListSchedulingInDTOs(List<Scheduling> schedulings){
        List<SchedulingDTO> schedulingDTOs = schedulings.stream()
        .map(svhed -> convertesSchedulingInDTO(svhed))
        .collect(Collectors.toList());
        return schedulingDTOs;
    }
    public static List<SchedulingResponse> convertesListDTOsInResponse(List<SchedulingDTO> schedulings){
        List<SchedulingResponse> schedulingResponses = schedulings.stream()
        .map(svhed -> convertesDTOInResponseScheduling(svhed))
        .collect(Collectors.toList());
        return schedulingResponses;
    }


}
