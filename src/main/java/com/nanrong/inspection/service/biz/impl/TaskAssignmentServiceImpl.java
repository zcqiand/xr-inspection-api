package com.nanrong.inspection.service.biz.impl;

import com.nanrong.inspection.domain.biz.TaskAssignment;
import com.nanrong.inspection.dto.biz.TaskAssignmentRequest;
import com.nanrong.inspection.dto.biz.TaskAssignmentResponse;
import com.nanrong.inspection.repository.biz.TaskAssignmentRepository;
import com.nanrong.inspection.shared.biz.AssignmentStatus;
import com.nanrong.inspection.shared.biz.AssignmentType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class TaskAssignmentServiceImpl {
    private final TaskAssignmentRepository taskAssignmentRepository;

    @Autowired
    public TaskAssignmentServiceImpl(
        TaskAssignmentRepository taskAssignmentRepository
    ) {
        this.taskAssignmentRepository = taskAssignmentRepository;
    }
    
    public TaskAssignmentResponse assignTask(TaskAssignmentRequest request) {
        TaskAssignment assignment = new TaskAssignment();
        // 设置任务分配属性
        assignment.setStatus(AssignmentStatus.PENDING);
        assignment.setType(AssignmentType.valueOf(request.getType()));
        
        TaskAssignment saved = taskAssignmentRepository.save(assignment);
        return convertToResponse(saved);
    }
    
    public TaskAssignmentResponse createAssignment(TaskAssignmentRequest request) {
        TaskAssignment assignment = new TaskAssignment();
        // 设置任务分配属性
        assignment.setStatus(AssignmentStatus.PENDING);
        assignment.setType(AssignmentType.valueOf(request.getType()));
        // 设置数据录入相关属性
        assignment.setDeviceRequirements(request.getDeviceRequirements());
        
        // 直接使用OffsetDateTime
        assignment.setPlannedStartTime(request.getPlannedStartTime());
        assignment.setPlannedEndTime(request.getPlannedEndTime());
        
        TaskAssignment saved = taskAssignmentRepository.save(assignment);
        return convertToResponse(saved);
    }
    
    @Transactional
    public void updateStatus(Long assignmentId, AssignmentStatus newStatus) {
        TaskAssignment assignment = taskAssignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new RuntimeException("任务分配不存在"));
        
        assignment.setStatus(newStatus);
        taskAssignmentRepository.save(assignment);
    }

    private TaskAssignmentResponse convertToResponse(TaskAssignment assignment) {
        TaskAssignmentResponse response = new TaskAssignmentResponse();
        // 设置所有响应属性
        response.setId(assignment.getId());
        if (assignment.getReport() != null) {
            response.setReportId(assignment.getReport().getId());
        }
        if (assignment.getAssignee() != null) {
            response.setAssigneeId(assignment.getAssignee().getId());
            // 使用getUsername()替代getName()
            response.setAssigneeName(assignment.getAssignee().getUsername());
        }
        response.setDeviceRequirements(assignment.getDeviceRequirements());
        response.setPlannedStartTime(assignment.getPlannedStartTime());
        response.setPlannedEndTime(assignment.getPlannedEndTime());
        response.setActualStartTime(assignment.getActualStartTime());
        response.setActualEndTime(assignment.getActualEndTime());
        response.setStatus(assignment.getStatus().name());
        response.setType(assignment.getType().name());
        return response;
    }
}