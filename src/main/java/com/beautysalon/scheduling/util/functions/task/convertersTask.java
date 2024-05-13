package com.beautysalon.scheduling.util.functions.task;

import java.util.List;
import java.util.stream.Collectors;

import com.beautysalon.scheduling.model.TaskType;
import com.beautysalon.scheduling.shared.TaskTypeDTO;
import com.beautysalon.scheduling.util.interfaces.instancesGlobal;
import com.beautysalon.scheduling.view.model.TaskTypeHttp.TaskTypeRequest;
import com.beautysalon.scheduling.view.model.TaskTypeHttp.TaskTypeResponse;
public class convertersTask  implements instancesGlobal{
    private convertersTask() {}

    public static TaskTypeDTO convertesRequestInDTOTaskType(TaskTypeRequest request){
        TaskTypeDTO taskTypeDTO =  mapper.map(request,TaskTypeDTO.class);
        return taskTypeDTO;
    }
    public static TaskTypeResponse convertesDTOInResponseTaskType(TaskTypeDTO dto){
        TaskTypeResponse taskTypeResponse =  mapper.map(dto,TaskTypeResponse.class);
        return taskTypeResponse;
    }
    public static TaskTypeDTO convertesTaskTypeInDTO(TaskType taskType){
        TaskTypeDTO taskTypeDTO =  mapper.map(taskType,TaskTypeDTO.class);
        return taskTypeDTO;
    }
    public static TaskType convertesDTOInTaskType(TaskTypeDTO dto){
        TaskType taskType =  mapper.map(dto,TaskType.class);
        return taskType;
    }
    public static List<TaskTypeDTO> convertesListTaskTypeInDTOs(List<TaskType> taskTypes){
        List<TaskTypeDTO> taskTypeDTOs = taskTypes.stream()
        .map(task -> convertesTaskTypeInDTO(task))
        .collect(Collectors.toList());
        return taskTypeDTOs;
    }
    public static List<TaskTypeResponse> convertesListDTOsInResponse(List<TaskTypeDTO> taskTypes){
        List<TaskTypeResponse> taskTypeResponses = taskTypes.stream()
        .map(task -> convertesDTOInResponseTaskType(task))
        .collect(Collectors.toList());
        return taskTypeResponses;
    }
    


}
