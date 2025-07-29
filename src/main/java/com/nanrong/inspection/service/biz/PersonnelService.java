package com.nanrong.inspection.service.biz;

import com.nanrong.inspection.domain.biz.WorkloadAnalysisVO;
import com.nanrong.inspection.dto.biz.CompetenceMatrixUpdateDTO;
import com.nanrong.inspection.dto.biz.QualificationWarningDTO;

import java.util.List;

public interface PersonnelService {
    List<QualificationWarningDTO> getExpiringQualifications();
    void updateCompetenceMatrix(CompetenceMatrixUpdateDTO dto);
    WorkloadAnalysisVO analyzeWorkload(String startDate, String endDate);
}