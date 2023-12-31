package com.products.service.elevatemartproductsservice.dto.mappers;

import com.products.service.elevatemartproductsservice.domain.Group;
import com.products.service.elevatemartproductsservice.dto.GroupDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

@Slf4j
public class GroupMapper {

    private static final String groupEntityObj="Group Entity Object";
    private static final String groupDtoObj="Group Dto Object";
    private static final ModelMapper modelMapper = new ModelMapper();
    public static GroupDto convertToDto(Group group) {
        log.info("Utilizing ModelMapper to convert :{} into :{}.",groupEntityObj,groupDtoObj);
        return modelMapper.map(group, GroupDto.class);
    }
    public static Group convertToEntity(GroupDto groupDto) {
        log.info("Utilizing ModelMapper to convert :{} into :{}",groupDtoObj,groupEntityObj);
        return modelMapper.map(groupDto, Group.class);
    }
}
