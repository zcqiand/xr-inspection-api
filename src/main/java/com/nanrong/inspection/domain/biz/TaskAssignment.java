package com.nanrong.inspection.domain.biz;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.Accessors;

import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;
import jakarta.persistence.*;

import com.nanrong.inspection.domain.biz.Report;
import com.nanrong.inspection.domain.sys.User;
import com.nanrong.inspection.shared.biz.AssignmentStatus;
import com.nanrong.inspection.shared.biz.AssignmentType;

import java.time.OffsetDateTime;

/**
 * 任务分配实体
 */
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
@DynamicUpdate
@Accessors(chain = true)
@Entity
@Table(name = "biz_task_assignment")
@Comment("任务分配表")
public class TaskAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "report_id", nullable = false)
    private Report report;
    
    @ManyToOne
    @JoinColumn(name = "assignee_id", nullable = false)
    private User assignee;
    
    private String deviceRequirements;

    private OffsetDateTime plannedStartTime;

    private OffsetDateTime plannedEndTime;

    private OffsetDateTime actualStartTime;

    private OffsetDateTime actualEndTime;
    
    @Enumerated(EnumType.STRING)
    private AssignmentStatus status;
    
    // 分配类型：自动/手动
    @Enumerated(EnumType.STRING)
    private AssignmentType type;
}