package com.nanrong.inspection.controller.biz;

import com.nanrong.inspection.domain.biz.WorkloadAnalysisVO;
import com.nanrong.inspection.dto.biz.CompetenceMatrixUpdateDTO;
import com.nanrong.inspection.dto.biz.QualificationWarningDTO;
import com.nanrong.inspection.service.biz.PersonnelService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Tag(name = "人员管理", description = "人员管理接口")
@RestController
@RequestMapping("/api/biz/personnel")
public class PersonnelController {

    private final PersonnelService personnelService;

    public PersonnelController(PersonnelService personnelService) {
        this.personnelService = personnelService;
    }

    @Operation(summary = "资质到期预警")
    @GetMapping("/qualification-warning")
    public ResponseEntity<List<QualificationWarningDTO>> getQualificationWarnings() {
        return ResponseEntity.ok(personnelService.getExpiringQualifications());
    }

    @Operation(summary = "更新能力矩阵")
    @PostMapping("/competence-matrix")
    public ResponseEntity<Void> updateCompetenceMatrix(@RequestBody CompetenceMatrixUpdateDTO dto) {
        personnelService.updateCompetenceMatrix(dto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "工作量分析")
    @GetMapping("/workload-analysis")
    public ResponseEntity<WorkloadAnalysisVO> analyzeWorkload(
        @Parameter(description = "开始日期") @RequestParam String startDate,
        @Parameter(description = "结束日期") @RequestParam String endDate) {
        return ResponseEntity.ok(personnelService.analyzeWorkload(startDate, endDate));
    }
}