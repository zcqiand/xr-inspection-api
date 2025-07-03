package com.nanrong.inspection.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nanrong.inspection.dto.test_report.TestReportApproveRequestDto;
import com.nanrong.inspection.dto.test_report.TestReportCreateRequestDto;
import com.nanrong.inspection.dto.test_report.TestReportDataEntryRequestDto;
import com.nanrong.inspection.dto.test_report.TestReportResponseDto;
import com.nanrong.inspection.dto.test_report.TestReportReviewRequestDto;
import com.nanrong.inspection.dto.test_report.TestReportUpdateRequestDto;
import com.nanrong.inspection.service.TestReportService;
import java.util.List;

@RestController
@RequestMapping("/api/test-reports")
@Tag(name = "检测报告管理", description = "检测报告的委托接样、数据录入、审核、批准及查询接口")
public class TestReportController {

  @Autowired
  private TestReportService testReportService;

  @PostMapping("/receive-sample")
  @Operation(summary = "创建委托接样", description = "创建新的检测报告")
  public ResponseEntity<TestReportResponseDto> createTestReport(
      @RequestBody TestReportCreateRequestDto requestDto) {
    TestReportResponseDto responseDto = testReportService.createTestReport(requestDto);
    return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
  }


  @PutMapping("/{reportId}")
  @Operation(summary = "修改检测报告", description = "修改检测报告的基本信息（仅包括检测日期、检测地址、检测环境、主要设备、结论、检测人）")
  public ResponseEntity<TestReportResponseDto> updateTestReport(@PathVariable Long reportId,
      @RequestBody TestReportUpdateRequestDto requestDto) {
    TestReportResponseDto responseDto = testReportService.updateTestReport(reportId, requestDto);
    return ResponseEntity.ok(responseDto);
  }

  @DeleteMapping("/{reportId}")
  @Operation(summary = "删除检测报告", description = "根据ID删除检测报告（委托接样阶段）")
  public ResponseEntity<Void> deleteTestReport(@PathVariable Long reportId) {
    testReportService.deleteTestReport(reportId);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("/{reportId}/data-entry")
  @Operation(summary = "数据录入修改检测报告", description = "在数据录入阶段修改检测报告的基本信息（检测日期、检测地址、检测环境、主要设备、结论、检测人）")
  public ResponseEntity<TestReportResponseDto> updateTestReportDataEntry(
      @PathVariable Long reportId, @RequestBody TestReportDataEntryRequestDto requestDto) {
    TestReportResponseDto responseDto =
        testReportService.updateTestReportDataEntry(reportId, requestDto);
    return ResponseEntity.ok(responseDto);
  }

  @PutMapping("/{reportId}/review")
  @Operation(summary = "报告审核", description = "更新检测报告的审核人信息")
  public ResponseEntity<TestReportResponseDto> reviewTestReport(@PathVariable Long reportId,
      @RequestBody TestReportReviewRequestDto requestDto) {
    TestReportResponseDto responseDto = testReportService.reviewTestReport(reportId, requestDto);
    return ResponseEntity.ok(responseDto);
  }

  @PutMapping("/{reportId}/approve")
  @Operation(summary = "报告批准", description = "更新检测报告的批准人信息和签发日期")
  public ResponseEntity<TestReportResponseDto> approveTestReport(@PathVariable Long reportId,
      @RequestBody TestReportApproveRequestDto requestDto) {
    TestReportResponseDto responseDto = testReportService.approveTestReport(reportId, requestDto);
    return ResponseEntity.ok(responseDto);
  }

  @GetMapping("/{reportId}")
  @Operation(summary = "查询检测报告详情", description = "根据ID查询检测报告的详细信息")
  public ResponseEntity<TestReportResponseDto> getTestReportById(@PathVariable Long reportId) {
    TestReportResponseDto responseDto = testReportService.getTestReportById(reportId);
    return ResponseEntity.ok(responseDto);
  }

  @GetMapping
  @Operation(summary = "查询所有检测报告", description = "查询所有检测报告列表")
  public ResponseEntity<List<TestReportResponseDto>> getAllTestReports() {
    List<TestReportResponseDto> responseDtos = testReportService.getAllTestReports();
    return ResponseEntity.ok(responseDtos);
  }

}
