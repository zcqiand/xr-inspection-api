package com.nanrong.inspection.service.biz.impl;
import com.nanrong.inspection.domain.biz.BQualification;
import com.nanrong.inspection.domain.biz.UserQualification;
import com.nanrong.inspection.domain.biz.WorkloadAnalysisVO;
import com.nanrong.inspection.domain.sys.User;
import com.nanrong.inspection.dto.biz.CompetenceMatrixUpdateDTO;
import com.nanrong.inspection.dto.biz.QualificationWarningDTO;
import com.nanrong.inspection.repository.sys.UserRepository;
import com.nanrong.inspection.service.biz.PersonnelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.time.temporal.ChronoUnit;

@Service
public class PersonnelServiceImpl implements PersonnelService {

    private final UserRepository userRepository;

    @Autowired
    public PersonnelServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<QualificationWarningDTO> getExpiringQualifications() {
        // 计算结束日期（当前日期+30天）
        java.time.LocalDate endDate = java.time.LocalDate.now().plusDays(30);
        
        // 获取原始数据并转换为DTO
        List<Object[]> results = userRepository.findExpiringQualifications(endDate);
        return results.stream().map(row -> {
            User user = (User) row[0];
            BQualification qualification = (BQualification) row[1];
            UserQualification userQualification = (UserQualification) row[2];
            
            // 计算剩余天数
            long daysRemaining = ChronoUnit.DAYS.between(OffsetDateTime.now(), userQualification.getExpiryDate());
            
            return new QualificationWarningDTO(
                user.getId(),
                user.getUsername(),
                qualification.getName(),
                userQualification.getExpiryDate(),
                daysRemaining
            );
        }).collect(java.util.stream.Collectors.toList());
    }

    @Override
    public void updateCompetenceMatrix(CompetenceMatrixUpdateDTO dto) {
        // 实现更新能力矩阵的逻辑
        userRepository.updateCompetenceLevel(dto.getUserId(), Long.parseLong(dto.getQualificationId()), dto.getNewLevel());
    }

    @Override
    public WorkloadAnalysisVO analyzeWorkload(String startDate, String endDate) {
        // 实现工作量分析的逻辑
        return userRepository.calculateWorkload(startDate, endDate);
    }
}