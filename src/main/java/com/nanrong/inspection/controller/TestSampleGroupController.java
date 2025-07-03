package com.nanrong.inspection.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nanrong.inspection.dto.test_sample_groups.TestSampleGroupCreateRequestDto;
import com.nanrong.inspection.dto.test_sample_groups.TestSampleGroupResponseDto;
import com.nanrong.inspection.dto.test_sample_groups.TestSampleGroupUpdateRequestDto;
import com.nanrong.inspection.service.TestSampleGroupService;

@RestController
@RequestMapping("/api/test-sample-groups")
@Tag(name = "检测样品组管理", description = "检测样品组的添加、修改、删除接口")
public class TestSampleGroupController {

  @Autowired
  private TestSampleGroupService testSampleGroupService;

  @PostMapping("/{reportId}")
  @Operation(summary = "添加检测样品组", description = "为指定检测报告添加新的检测样品组")
  public ResponseEntity<TestSampleGroupResponseDto> addTestSampleGroup(@PathVariable Long reportId,
      @RequestBody TestSampleGroupCreateRequestDto requestDto) {
    TestSampleGroupResponseDto responseDto =
        testSampleGroupService.addTestSampleGroup(reportId, requestDto);
    return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
  }

  @PutMapping("/{sampleGroupId}")
  @Operation(summary = "修改检测样品组", description = "修改指定的检测样品组信息")
  public ResponseEntity<TestSampleGroupResponseDto> updateTestSampleGroup(
      @PathVariable Long sampleGroupId, @RequestBody TestSampleGroupUpdateRequestDto requestDto) {
    TestSampleGroupResponseDto responseDto =
        testSampleGroupService.updateTestSampleGroup(sampleGroupId, requestDto);
    return ResponseEntity.ok(responseDto);
  }

  @DeleteMapping("/{sampleGroupId}")
  @Operation(summary = "删除检测样品组", description = "删除指定的检测样品组")
  public ResponseEntity<Void> deleteTestSampleGroup(@PathVariable Long sampleGroupId) {
    testSampleGroupService.deleteTestSampleGroup(sampleGroupId);
    return ResponseEntity.noContent().build();
  }
}
