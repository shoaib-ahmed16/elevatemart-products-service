package com.products.service.elevatemartproductsservice.services;

import com.products.service.elevatemartproductsservice.domain.Group;
import com.products.service.elevatemartproductsservice.dto.GroupDto;
import com.products.service.elevatemartproductsservice.dto.mappers.GroupMapper;
import com.products.service.elevatemartproductsservice.exception.GroupNotFoundException;
import com.products.service.elevatemartproductsservice.exception.GroupUnknownServerErrorException;
import com.products.service.elevatemartproductsservice.repository.GroupRepository;
import com.products.service.elevatemartproductsservice.utils.AttributeErrorMessages;
import com.products.service.elevatemartproductsservice.utils.GroupErrorMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public final class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepo;
    @Override
    public void createGroup(Group group) {
        log.info("Initialize the process of creating Group and saving into the database.");
        try{
            groupRepo.save(group);
            log.info("Group is successfully save in the database :{}",group);
        }
        catch (Exception exc){
            log.error(GroupErrorMessages.UNKNOWNERROR.getMessage());
            throw new GroupUnknownServerErrorException(GroupErrorMessages.UNKNOWNERROR.getMessage()+exc.getMessage()+" : "+group);
        }
    }

    @Override
    public void updateGroup(Long groupId,Group group) {
        log.info("Initialize the  process of updating Group.");
        try{
            Group grp= groupRepo.findById(group.getGroupId())
                    .orElseThrow(()->{
                        log.error(GroupErrorMessages.NOTFOUNDBYID.getMessage()+group.getGroupId());
                        throw new GroupNotFoundException(GroupErrorMessages.NOTFOUNDBYID.getMessage()+group.getGroupId());
                    });
            grp.setProductList(group.getProductList());
            grp.setName(group.getName());
            grp.setAttributeList(group.getAttributeList());
            groupRepo.save(grp);
            log.info("Group fields is successfully updated and saved in the database :{}",grp);
        }
        catch (Exception exc){
            log.error(GroupErrorMessages.UNKNOWNERROR.getMessage());
            throw new GroupUnknownServerErrorException(GroupErrorMessages.UNKNOWNERROR.getMessage()+exc.getMessage()+" : "+group);
        }
    }

    @Override
    public void updateGroupActiveStatus(Long groupId, Boolean status) {
        log.info("Initialize the  process of updating Group Status.");
        try{
            log.info("Initialize the  process of fetching Group Details by Group Id: {}",groupId);
            Group grp= groupRepo.findById(groupId)
                    .orElseThrow(()->{
                        log.error(GroupErrorMessages.NOTFOUNDBYID.getMessage()+groupId);
                        throw new GroupNotFoundException(GroupErrorMessages.NOTFOUNDBYID.getMessage()+groupId);
                    });
            log.info("Successfully fetched Group details for Group ID: {}", groupId);
            log.info("Group status field updating from :{} to : {} and saved in the database",grp.getIsActive(),status);
            grp.setIsActive(status);
            groupRepo.save(grp);
            log.info("Group status field is successfully updated to : {} and saved in the database",status);
        }
        catch (Exception exc){
            log.error(GroupErrorMessages.UNKNOWNERROR.getMessage());
            throw new GroupUnknownServerErrorException(GroupErrorMessages.UNKNOWNERROR.getMessage()+exc.getMessage());
        }
    }

    @Override
    public GroupDto getGroupById(Long groupId) {
        try{
            log.info("Initialize the  process of fetching Group Details by Attribute Id: {}",groupId);
            Group grp= groupRepo.findById(groupId)
                    .orElseThrow(()->{
                        log.error(GroupErrorMessages.NOTFOUNDBYID.getMessage()+groupId);
                        throw new GroupNotFoundException(GroupErrorMessages.NOTFOUNDBYID.getMessage()+groupId);
                    });
            log.info("Successfully fetch Group details for Group ID: {}", groupId);
            log.info("Group Record found for the Group Id : {}. Returning the Group Object : {}",groupId,grp);
            return GroupMapper.convertToDto(grp);
        }
        catch (Exception exc){
            log.error(GroupErrorMessages.UNKNOWNERROR.getMessage());
            throw new GroupUnknownServerErrorException(GroupErrorMessages.UNKNOWNERROR.getMessage()+exc.getMessage());
        }
    }

    @Override
    public List<GroupDto> getAllGroups() {
        try{
            log.info("Initialize the  process of fetching All Group Details from the Database.");
            List<Group> groupList = groupRepo.findAll();
            if(groupList.isEmpty()){
                log.error(GroupErrorMessages.NORECORDSFOUND.getMessage());
                throw new GroupNotFoundException(GroupErrorMessages.NORECORDSFOUND.getMessage());
            }
            log.info("Successfully fetched All Group Details from the Database.");
            log.info("Initialize the process of Converting List<Group> to List<GroupDto>  list:");
            var groupDtoList = new ArrayList<GroupDto>();
            for(Group a:groupList){
                groupDtoList.add(GroupMapper.convertToDto(a));
            }
            log.info("Successfully Converted List<Group> to List<GroupDto>  list");
            log.info("Returning the Group Record:{}",groupDtoList);
            return groupDtoList;
        }
        catch (Exception exc){
            log.error(GroupErrorMessages.UNKNOWNERROR.getMessage());
            throw new GroupUnknownServerErrorException(GroupErrorMessages.UNKNOWNERROR.getMessage()+exc.getMessage());
        }
    }

    @Override
    public GroupDto getGroupByName(String groupName) {
        try{
            log.info("Initialize the  process of fetching Group Details by Group Name: {}",groupName);
            Group grp= groupRepo.findByName(groupName)
                    .orElseThrow(()->{
                        log.error(GroupErrorMessages.NOTFOUNDBYID.getMessage()+groupName);
                        throw new GroupNotFoundException(GroupErrorMessages.NOTFOUNDBYID.getMessage()+groupName);
                    });
            log.info("Successfully fetch Group details for Group Name: {}", groupName);
            log.info("Group Record found for the Group Name : {}. Returning the Group Object : {}",groupName,grp);
            return GroupMapper.convertToDto(grp);
        }
        catch (Exception exc){
            log.error(GroupErrorMessages.UNKNOWNERROR.getMessage());
            throw new GroupUnknownServerErrorException(GroupErrorMessages.UNKNOWNERROR.getMessage()+exc.getMessage());
        }
    }



    @Override
    public void deleteGroupById(Long groupId) {
        try{
            log.info("Initialize the  process of fetching Group Details by Group Id: {}",groupId);
            Group grp= groupRepo.findById(groupId).orElseThrow(()->{
                log.error(GroupErrorMessages.NOTFOUNDBYID.getMessage()+groupId);
                throw new GroupNotFoundException(AttributeErrorMessages.NOTFOUNDBYID.getMessage()+groupId+". Please provide the correct Group Id to Delete the Group.");
            });
            log.info("Successfully fetched Group details for Group ID: {}", groupId);
            log.info("Group Record found for the Group Id : {}. Deleting the Group Object : {}",groupId,grp);
            groupRepo.delete(grp);
            log.info("Group Record :{} Successfully deleted  for the Group Id : {}.",grp,groupId);
        }
        catch (Exception exc){
            log.error(GroupErrorMessages.UNKNOWNERROR.getMessage());
            throw new GroupUnknownServerErrorException(AttributeErrorMessages.UNKNOWNERROR.getMessage()+exc.getMessage());
        }
    }

    @Override
    public void deleteMultipleGroup(List<Long> groupIds) {
        try{
            Iterable<Long> iterator = groupIds;
            log.info("Initialize the  process of fetching Group Details by Group Ids and deleting: {}",iterator);
            groupRepo.deleteAllByIdInBatch(iterator);
            log.info("Group Records Successfully deleted  for the List of Group Ids : {}.",groupIds);
        }
        catch (Exception exc){
            log.error(GroupErrorMessages.UNKNOWNERROR.getMessage());
            throw new GroupUnknownServerErrorException(AttributeErrorMessages.UNKNOWNERROR.getMessage()+exc.getMessage());
        }
    }
}
