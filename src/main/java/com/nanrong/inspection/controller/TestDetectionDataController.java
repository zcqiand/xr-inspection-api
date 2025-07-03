package com.nanrong.inspection.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.nanrong.inspection.dto.test_detection_data.TestDetectionDataCreateRequestDto;
import com.nanrong.inspection.dto.test_detection_data.TestDetectionDataResponseDto;
import com.nanrong.inspection.dto.test_detection_data.TestDetectionDataUpdateRequestDto;
import com.nanrong.inspection.service.TestDetectionDataService;
import java.util.List;

/**
 * 检测数据控制器
 */
@Tag(name = "检测数据管理", description = "检测数据API")
@RestController
@RequestMapping("/api/test-detection-data")
public class TestDetectionDataController {

  @Autowired
  private TestDetectionDataService testDetectionDataService;

  @Operation(summary = "创建检测数据")
  @PostMapping
  public ResponseEntity<TestDetectionDataResponseDto> createTestDetectionData(
      @Valid @RequestBody TestDetectionDataCreateRequestDto requestDto) {
    TestDetectionDataResponseDto responseDto =
        testDetectionDataService.createTestDetectionData(requestDto);
    return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
  }

  @Operation(summary = "更新检测数据")
  @PutMapping
  public ResponseEntity<TestDetectionDataResponseDto> updateTestDetectionData(
      @Valid @RequestBody TestDetectionDataUpdateRequestDto requestDto) {
    TestDetectionDataResponseDto responseDto =
        testDetectionDataService.updateTestDetectionData(requestDto);
    return ResponseEntity.ok(responseDto);
  }

  @Operation(summary = "删除检测数据")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTestDetectionData(@PathVariable Long id) {
    testDetectionDataService.deleteTestDetectionData(id);
    return ResponseEntity.noContent().build();
  }

  @Operation(summary = "根据ID获取检测数据")
  @GetMapping("/{id}")
  public ResponseEntity<TestDetectionDataResponseDto> getTestDetectionDataById(
      @PathVariable Long id) {
    TestDetectionDataResponseDto responseDto =
        testDetectionDataService.getTestDetectionDataById(id);
    return ResponseEntity.ok(responseDto);
  }

  @Operation(summary = "根据检测结果ID获取检测数据列表")
  @GetMapping("/by-test-result/{testResultId}")
  public ResponseEntity<List<TestDetectionDataResponseDto>> getTestDetectionDataByTestResultId(
      @PathVariable Long testResultId) {
    List<TestDetectionDataResponseDto> responseDtos =
        testDetectionDataService.getTestDetectionDataByTestResultId(testResultId);
    return ResponseEntity.ok(responseDtos);
  }
}
