package com.nanrong.inspection.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.nanrong.inspection.dto.test_detection_data.TestDetectionDataCreateRequestDto;
import com.nanrong.inspection.dto.test_detection_data.TestDetectionDataResponseDto;
import com.nanrong.inspection.dto.test_detection_data.TestDetectionDataUpdateRequestDto;
import com.nanrong.inspection.model.TestDetectionData;
import com.nanrong.inspection.model.TestReport;
import com.nanrong.inspection.model.TestResult;
import com.nanrong.inspection.repository.TestDetectionDataRepository;
import com.nanrong.inspection.repository.TestReportRepository;
import com.nanrong.inspection.repository.TestResultRepository;
import com.nanrong.inspection.util.ReportCodeConstants;
import com.nanrong.inspection.util.TestItemCodeConstants;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 检测数据服务
 */
@Service
public class TestDetectionDataService {

  @Autowired
  private TestDetectionDataRepository testDetectionDataRepository;

  @Autowired
  private TestResultRepository testResultRepository;

  @Autowired
  private TestReportRepository testReportRepository;

  @Transactional
  public TestDetectionDataResponseDto createTestDetectionData(
      TestDetectionDataCreateRequestDto requestDto) {
    TestResult testResult = testResultRepository.findById(requestDto.getTestResultId()).orElseThrow(
        () -> new EntityNotFoundException("未找到检测结果，ID: " + requestDto.getTestResultId()));

    TestReport testReport = testResult.getSampleGroup().getTestReport();
    String reportCode = testReport.getReportNameSetting().getReportCode();
    String testItemCode = testResult.getTestItemSetting().getTestItemCode();

    TestDetectionData testDetectionData =
        TestDetectionData.builder().testResult(testResult).build();

    // 根据检测报告名称和检测项目维护特定字段
    if (ReportCodeConstants.GANGJJXLJ.equals(reportCode)
        && TestItemCodeConstants.JI_XIAN_KANG_LA_QIANG_DU.equals(testItemCode)) {
      testDetectionData.setFractureLocation(requestDto.getFractureLocation());
    } else if (ReportCodeConstants.GANGJHJ.equals(reportCode)
        && (TestItemCodeConstants.KANG_LA_QIANG_DU.equals(testItemCode))) {
      testDetectionData.setFractureDistanceToWeld(requestDto.getFractureDistanceToWeld());
      testDetectionData.setFractureCharacteristic(requestDto.getFractureCharacteristic());
    }

    TestDetectionData savedData = testDetectionDataRepository.save(testDetectionData);
    return convertToDto(savedData);
  }

  @Transactional
  public TestDetectionDataResponseDto updateTestDetectionData(
      TestDetectionDataUpdateRequestDto requestDto) {
    TestDetectionData existingData = testDetectionDataRepository.findById(requestDto.getId())
        .orElseThrow(() -> new EntityNotFoundException("未找到检测数据，ID: " + requestDto.getId()));

    TestResult testResult = testResultRepository.findById(requestDto.getTestResultId()).orElseThrow(
        () -> new EntityNotFoundException("未找到检测结果，ID: " + requestDto.getTestResultId()));

    TestReport testReport = testResult.getSampleGroup().getTestReport();
    String reportCode = testReport.getReportNameSetting().getReportCode();
    String testItemCode = testResult.getTestItemSetting().getTestItemCode();

    existingData.setTestResult(testResult);

    // 根据检测报告名称和检测项目维护特定字段
    if (ReportCodeConstants.GANGJJXLJ.equals(reportCode)
        && TestItemCodeConstants.JI_XIAN_KANG_LA_QIANG_DU.equals(testItemCode)) {
      existingData.setFractureLocation(requestDto.getFractureLocation());
      existingData.setFractureDistanceToWeld(null); // 清空不相关字段
      existingData.setFractureCharacteristic(null); // 清空不相关字段
    } else if (ReportCodeConstants.GANGJHJ.equals(reportCode)
        && (TestItemCodeConstants.KANG_LA_QIANG_DU.equals(testItemCode))) {
      existingData.setFractureDistanceToWeld(requestDto.getFractureDistanceToWeld());
      existingData.setFractureCharacteristic(requestDto.getFractureCharacteristic());
      existingData.setFractureLocation(null); // 清空不相关字段
    } else {
      // 如果不符合特定条件，清空所有特殊字段
      existingData.setFractureLocation(null);
      existingData.setFractureDistanceToWeld(null);
      existingData.setFractureCharacteristic(null);
    }

    TestDetectionData updatedData = testDetectionDataRepository.save(existingData);
    return convertToDto(updatedData);
  }

  @Transactional
  public void deleteTestDetectionData(Long id) {
    if (!testDetectionDataRepository.existsById(id)) {
      throw new EntityNotFoundException("未找到检测数据，ID: " + id);
    }
    testDetectionDataRepository.deleteById(id);
  }

  public TestDetectionDataResponseDto getTestDetectionDataById(Long id) {
    TestDetectionData testDetectionData = testDetectionDataRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("未找到检测数据，ID: " + id));
    return convertToDto(testDetectionData);
  }

  public List<TestDetectionDataResponseDto> getTestDetectionDataByTestResultId(Long testResultId) {
    List<TestDetectionData> detectionDataList =
        testDetectionDataRepository.findAll().stream().filter(data -> data.getTestResult() != null
            && data.getTestResult().getId().equals(testResultId)).collect(Collectors.toList());
    return detectionDataList.stream().map(this::convertToDto).collect(Collectors.toList());
  }

  private TestDetectionDataResponseDto convertToDto(TestDetectionData testDetectionData) {
    return TestDetectionDataResponseDto.builder().id(testDetectionData.getId())
        .testResultId(
            testDetectionData.getTestResult() != null ? testDetectionData.getTestResult().getId()
                : null)
        .fractureDistanceToWeld(testDetectionData.getFractureDistanceToWeld())
        .fractureCharacteristic(testDetectionData.getFractureCharacteristic())
        .fractureLocation(testDetectionData.getFractureLocation()).build();
  }
}
