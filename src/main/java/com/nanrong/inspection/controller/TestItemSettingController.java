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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.nanrong.inspection.dto.test_item_setting.TestItemSettingCreateRequestDto;
import com.nanrong.inspection.dto.test_item_setting.TestItemSettingResponseDto;
import com.nanrong.inspection.dto.test_item_setting.TestItemSettingUpdateRequestDto;
import com.nanrong.inspection.service.TestItemSettingService;

@RestController
@RequestMapping("/api/test-item-settings")
@Tag(name = "检测项目设置管理", description = "检测项目设置的增删改查接口")
public class TestItemSettingController {

  @Autowired
  private TestItemSettingService testItemSettingService;

  @PostMapping
  @Operation(summary = "创建检测项目设置", description = "创建新的检测项目设置")
  public ResponseEntity<TestItemSettingResponseDto> createTestItemSetting(
      @RequestBody TestItemSettingCreateRequestDto requestDto) {
    TestItemSettingResponseDto responseDto =
        testItemSettingService.createTestItemSetting(requestDto);
    return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  @Operation(summary = "更新检测项目设置", description = "根据ID更新检测项目设置")
  public ResponseEntity<TestItemSettingResponseDto> updateTestItemSetting(@PathVariable Long id,
      @RequestBody TestItemSettingUpdateRequestDto requestDto) {
    TestItemSettingResponseDto responseDto =
        testItemSettingService.updateTestItemSetting(id, requestDto);
    return ResponseEntity.ok(responseDto);
  }

  @GetMapping("/{id}")
  @Operation(summary = "获取检测项目设置", description = "根据ID获取检测项目设置详情")
  public ResponseEntity<TestItemSettingResponseDto> getTestItemSettingById(@PathVariable Long id) {
    TestItemSettingResponseDto responseDto = testItemSettingService.getTestItemSettingById(id);
    return ResponseEntity.ok(responseDto);
  }

  @GetMapping
  @Operation(summary = "获取所有检测项目设置", description = "获取所有检测项目设置列表")
  public ResponseEntity<List<TestItemSettingResponseDto>> getAllTestItemSettings() {
    List<TestItemSettingResponseDto> responseDtos = testItemSettingService.getAllTestItemSettings();
    return ResponseEntity.ok(responseDtos);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "删除检测项目设置", description = "根据ID删除检测项目设置")
  public ResponseEntity<Void> deleteTestItemSetting(@PathVariable Long id) {
    testItemSettingService.deleteTestItemSetting(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/exists")
  @Operation(summary = "判断检测项目是否存在", description = "根据检测项目名称判断检测项目是否存在")
  public ResponseEntity<Boolean> existsByTestItemName(@RequestParam String testItemName) {
    boolean exists = testItemSettingService.existsByTestItemName(testItemName);
    return ResponseEntity.ok(exists);
  }
}
