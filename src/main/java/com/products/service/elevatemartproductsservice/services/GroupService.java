package com.products.service.elevatemartproductsservice.services;

import com.products.service.elevatemartproductsservice.domain.Group;
import com.products.service.elevatemartproductsservice.dto.GroupDto;

import java.util.List;

public sealed interface  GroupService permits GroupServiceImpl {
    void createGroup(Group group);

    void updateGroup(Long groupId,Group group);

    GroupDto getGroupById(Long groupId);
    List<GroupDto> getAllGroups();

    GroupDto getGroupByName(String groupName);

    void updateGroupActiveStatus(Long groupId,Boolean status);

    void deleteGroupById(Long groupId);

    void deleteMultipleGroup(List<Long> groupIds);



}
