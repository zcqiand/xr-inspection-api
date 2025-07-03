package com.nanrong.inspection.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nanrong.inspection.dto.test_report_name_setting.TestReportNameSettingCreateRequestDto;
import com.nanrong.inspection.dto.test_report_name_setting.TestReportNameSettingResponseDto;
import com.nanrong.inspection.dto.test_report_name_setting.TestReportNameSettingUpdateRequestDto;
import com.nanrong.inspection.service.TestReportNameSettingService;

@RestController
@RequestMapping("/api/test-report-name-settings")
@Tag(name = "检测报告名称设置管理", description = "检测报告名称设置的增删改查接口")
public class TestReportNameSettingController {

  @Autowired
  private TestReportNameSettingService testReportNameSettingService;

  @PostMapping
  @Operation(summary = "创建检测报告名称设置", description = "创建新的检测报告名称设置")
  public ResponseEntity<TestReportNameSettingResponseDto> createTestReportNameSetting(
      @RequestBody TestReportNameSettingCreateRequestDto requestDto) {
    TestReportNameSettingResponseDto responseDto =
        testReportNameSettingService.createTestReportNameSetting(requestDto);
    return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  @Operation(summary = "更新检测报告名称设置", description = "根据ID更新检测报告名称设置")
  public ResponseEntity<TestReportNameSettingResponseDto> updateTestReportNameSetting(
      @PathVariable Long id, @RequestBody TestReportNameSettingUpdateRequestDto requestDto) {
    TestReportNameSettingResponseDto responseDto =
        testReportNameSettingService.updateTestReportNameSetting(id, requestDto);
    return ResponseEntity.ok(responseDto);
  }

  @GetMapping("/{id}")
  @Operation(summary = "获取检测报告名称设置", description = "根据ID获取检测报告名称设置详情")
  public ResponseEntity<TestReportNameSettingResponseDto> getTestReportNameSettingById(
      @PathVariable Long id) {
    TestReportNameSettingResponseDto responseDto =
        testReportNameSettingService.getTestReportNameSettingById(id);
    return ResponseEntity.ok(responseDto);
  }

  @GetMapping
  @Operation(summary = "获取所有检测报告名称设置", description = "获取所有检测报告名称设置列表")
  public ResponseEntity<List<TestReportNameSettingResponseDto>> getAllTestReportNameSettings() {
    List<TestReportNameSettingResponseDto> responseDtos =
        testReportNameSettingService.getAllTestReportNameSettings();
    return ResponseEntity.ok(responseDtos);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "删除检测报告名称设置", description = "根据ID删除检测报告名称设置")
  public ResponseEntity<Void> deleteTestReportNameSetting(@PathVariable Long id) {
    testReportNameSettingService.deleteTestReportNameSetting(id);
    return ResponseEntity.noContent().build();
  }
}
