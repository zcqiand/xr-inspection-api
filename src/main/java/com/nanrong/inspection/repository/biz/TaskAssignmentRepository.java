package com.nanrong.inspection.repository.biz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nanrong.inspection.domain.biz.TaskAssignment;

@Repository
public interface TaskAssignmentRepository extends JpaRepository<TaskAssignment, Long> {
    // 可以添加自定义查询方法
}