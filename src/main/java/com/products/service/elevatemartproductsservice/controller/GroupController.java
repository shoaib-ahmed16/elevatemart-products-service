package com.products.service.elevatemartproductsservice.controller;

import com.products.service.elevatemartproductsservice.domain.Group;
import com.products.service.elevatemartproductsservice.exception.GroupNullObjectException;
import com.products.service.elevatemartproductsservice.services.GroupService;
import com.products.service.elevatemartproductsservice.utils.Operation;
import com.products.service.elevatemartproductsservice.utils.ResponseSuccess;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("api/v1/group")
public class GroupController {

    @Autowired
    private GroupService groupService;
    @PostMapping("/save")
    public ResponseSuccess createGroup(@RequestBody Group group){
        log.info("Received a Group Creation Request Call. Initiating Attribute Creation Process. :{}",group);
        if(Objects.isNull(group)){
            log.error("Failed to process request: Received an empty Group creation Object - {}", group);
            throw new GroupNullObjectException("Group Creation");
        }
        groupService.createGroup(group);
        return  new ResponseSuccess(Operation.SAVED.getMessage(),Operation.GROUP.getType()+Operation.SAVED.getOperation(),Operation.SAVED.getStatusCode());

    }

    @PostMapping("/update")
    public ResponseSuccess updateGroup(@RequestBody Group group, @RequestParam("id") Long groupId){
        log.info("Received a Group fields Updation Request Call. Initiating Group Updating Process. :{}",group);
        if(Objects.isNull(group)){
            log.error("Failed to process request: Received an empty Group creation Object - {}", group);
            throw new GroupNullObjectException("Group Updation");
        }
        groupService.updateGroup(groupId,group);
        return  new ResponseSuccess(Operation.UPDATE.getMessage(),Operation.GROUP.getType()+Operation.UPDATE.getOperation(),Operation.UPDATE.getStatusCode());
    }

    @PutMapping("/updateStatus")
    public ResponseSuccess updateStatusGroup(@RequestParam("id") Long  groupId, @RequestParam("status") Boolean status){
        log.info("Received a Group Status update  Request Call. Initiating Group Status Updating Process. with Group Id:{}, Status :{}",groupId,status);
        if(Objects.isNull(groupId) && Objects.isNull(status)){
            log.error("Failed to process request: Received an empty Group Id :{} Or  Status field : {}",groupId,status);
            throw new GroupNullObjectException("Group Status Update");
        }
        groupService.updateGroupActiveStatus(groupId,status);
        return  new ResponseSuccess(Operation.UPDATEACTIVESTATUS.getMessage(),Operation.GROUP.getType()+Operation.UPDATEACTIVESTATUS.getOperation(),Operation.UPDATEACTIVESTATUS.getStatusCode());
    }

    @GetMapping("/fetchById")
    public ResponseSuccess fetchGroupById(@RequestParam("id") Long  groupId){
        log.info("Received a Group Detail Fetch By Id  Request Call. Initiating Group Fetching Process for  Group Id:{}",groupId);
        if(groupId ==0){
            log.error("Failed to process request: Received an empty Group Id :{}.",groupId);
            throw new GroupNullObjectException("Group Details Fetch By Id:"+groupId);
        }
        return  new ResponseSuccess(Operation.FETCHBYID.getMessage(),Operation.GROUP.getType()+Operation.FETCHBYID.getOperation(),Operation.FETCHBYID.getStatusCode(),groupService.getGroupById(groupId));
    }

    @GetMapping("/fetchByName")
    public ResponseSuccess fetchGroupByName(@RequestParam("name") String  groupName){
        log.info("Received a Group Detail Fetch By Id  Request Call. Initiating Group Fetching Process for  Group Name:{}",groupName);
        if(Objects.isNull(groupName) || groupName.trim().equals("")){
            log.error("Failed to process request: Received an empty Group Name :{}.",groupName);
            throw new GroupNullObjectException("Group Details Fetch By Name:"+groupName);
        }
        return  new ResponseSuccess(Operation.FETCHBYNAME.getMessage(),Operation.GROUP.getType()+Operation.FETCHBYNAME.getOperation(),Operation.FETCHBYNAME.getStatusCode(),groupService.getGroupByName(groupName));
    }

    @GetMapping("/fetchAll")
    public ResponseSuccess fetchAllGroup(){
        log.info("Received a All Group Detail Fetch Request Call. Initiating All Group Fetching Process.");
        return  new ResponseSuccess(Operation.FETCHALL.getMessage(),Operation.GROUP.getType()+Operation.FETCHALL.getOperation(),Operation.FETCHALL.getStatusCode(),groupService.getAllGroups());
    }

    @DeleteMapping("/deleteById")
    public ResponseSuccess deleteGroupById(@RequestParam("id") Long groupId){
        log.info("Received Request Call to delete the Group by Id: {}. Initiating Deleting Group record process.",groupId);
        if(groupId!=0){
            log.error("Failed to process request: Received an empty Group Id :{}.",groupId);
            throw new GroupNullObjectException("GroupId is either not valid or ");
        }
        groupService.deleteGroupById(groupId);
        return  new ResponseSuccess(Operation.DELETEBYID.getMessage(),Operation.GROUP.getType()+Operation.DELETEBYID.getOperation(),Operation.DELETEBYID.getStatusCode());
    }

    @DeleteMapping("/deleteMulitple")
    public ResponseSuccess deleteMultipleGroup(@RequestBody List<Long> groupIds){
        log.info("Received Request Call to delete the Group by Ids: {}. Initiating Deleting Group record process.",groupIds);
        if(Objects.isNull(groupIds) && groupIds.isEmpty()){
            log.error("Failed to process request: Received an empty Group Ids :{}.",groupIds);
            throw new GroupNullObjectException("GroupIds List is either Empty or ");
        }
        groupService.deleteMultipleGroup(groupIds);
        return  new ResponseSuccess(Operation.DELETEMULTIPLE.getMessage(),Operation.GROUP.getType()+Operation.DELETEMULTIPLE.getOperation(),Operation.DELETEMULTIPLE.getStatusCode());
    }

}
