package com.nanrong.inspection.service;

import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.nanrong.inspection.dto.test_sample_groups.TestResultDto;
import com.nanrong.inspection.dto.test_sample_groups.TestSampleGroupCreateRequestDto;
import com.nanrong.inspection.dto.test_sample_groups.TestSampleGroupResponseDto;
import com.nanrong.inspection.dto.test_sample_groups.TestSampleGroupUpdateRequestDto;
import com.nanrong.inspection.model.TestItemSetting;
import com.nanrong.inspection.model.TestReport;
import com.nanrong.inspection.model.TestResult;
import com.nanrong.inspection.model.TestSampleGroup;
import com.nanrong.inspection.repository.TestItemSettingRepository;
import com.nanrong.inspection.repository.TestReportRepository;
import com.nanrong.inspection.repository.TestSampleGroupRepository;
import com.nanrong.inspection.util.ReportCodeConstants;
import com.nanrong.inspection.util.TestItemCodeConstants;

@Service
public class TestSampleGroupService {

  @Autowired
  private TestReportRepository testReportRepository;
  @Autowired
  private TestItemSettingRepository testItemSettingRepository;
  @Autowired
  private TestSampleGroupRepository testSampleGroupRepository;

  @Transactional
  public TestSampleGroupResponseDto addTestSampleGroup(Long reportId,
      TestSampleGroupCreateRequestDto requestDto) {
    TestReport testReport = testReportRepository.findById(reportId).orElseThrow(
        () -> new EntityNotFoundException("TestReport not found with id: " + reportId));

    TestSampleGroup testSampleGroup = new TestSampleGroup();
    BeanUtils.copyProperties(requestDto, testSampleGroup);
    testSampleGroup.setTestReport(testReport);

    // Apply conditional logic based on test report code
    String reportCode = testReport.getReportNameSetting() != null
        ? testReport.getReportNameSetting().getReportCode()
        : "";
    applyConditionalLogicToSampleGroup(testSampleGroup, reportCode);

    if (requestDto.getTestResults() != null && !requestDto.getTestResults().isEmpty()) {
      List<TestResult> testResults = requestDto.getTestResults().stream().map(resultDto -> {
        TestResult testResult = new TestResult();
        BeanUtils.copyProperties(resultDto, testResult);
        testResult.setSampleGroup(testSampleGroup);

        if (resultDto.getTestItemSettingId() != null) {
          TestItemSetting testItemSetting =
              testItemSettingRepository.findById(resultDto.getTestItemSettingId())
                  .orElseThrow(() -> new EntityNotFoundException("TestItemSetting not found"));
          testResult.setTestItemSetting(testItemSetting);

          // Apply conditional logic for bending properties based on test item code
          if (TestItemCodeConstants.JI_XIAN_KANG_LA_QIANG_DU
              .equals(testItemSetting.getTestItemCode())) {
            testResult.setBendingHeadDiameter(resultDto.getBendingHeadDiameter());
            testResult.setBendingAngle(resultDto.getBendingAngle());
          } else {
            testResult.setBendingHeadDiameter(null);
            testResult.setBendingAngle(null);
          }
        }
        return testResult;
      }).collect(Collectors.toList());
      testSampleGroup.setTestResults(testResults);
    } else {
      testSampleGroup.setTestResults(new ArrayList<>());
    }

    testReport.getSampleGroups().add(testSampleGroup);
    testReportRepository.save(testReport); // Save the report to cascade save the sample group
    return convertToDto(testSampleGroup);
  }

  @Transactional
  public TestSampleGroupResponseDto updateTestSampleGroup(Long sampleGroupId,
      TestSampleGroupUpdateRequestDto requestDto) {
    TestSampleGroup testSampleGroup = testSampleGroupRepository.findById(sampleGroupId).orElseThrow(
        () -> new EntityNotFoundException("TestSampleGroup not found with id: " + sampleGroupId));

    BeanUtils.copyProperties(requestDto, testSampleGroup, "id", "testReportId");

    // Apply conditional logic based on test report code
    String reportCode = testSampleGroup.getTestReport() != null
        && testSampleGroup.getTestReport().getReportNameSetting() != null
            ? testSampleGroup.getTestReport().getReportNameSetting().getReportCode()
            : "";
    applyConditionalLogicToSampleGroup(testSampleGroup, reportCode);

    // Handle test results
    if (requestDto.getTestResults() != null) {
      // Remove results not in the request
      testSampleGroup.getTestResults()
          .removeIf(existingResult -> requestDto.getTestResults().stream()
              .noneMatch(dto -> dto.getId() != null && dto.getId().equals(existingResult.getId())));

      requestDto.getTestResults().forEach(resultDto -> {
        if (resultDto.getId() == null) { // New TestResult
          TestResult newTestResult = new TestResult();
          BeanUtils.copyProperties(resultDto, newTestResult);
          newTestResult.setSampleGroup(testSampleGroup);
          if (resultDto.getTestItemSettingId() != null) {
            TestItemSetting testItemSetting =
                testItemSettingRepository.findById(resultDto.getTestItemSettingId())
                    .orElseThrow(() -> new EntityNotFoundException("TestItemSetting not found"));
            newTestResult.setTestItemSetting(testItemSetting);

            // Apply conditional logic for bending properties based on test item code
            if (TestItemCodeConstants.JI_XIAN_KANG_LA_QIANG_DU
                .equals(testItemSetting.getTestItemCode())) {
              newTestResult.setBendingHeadDiameter(resultDto.getBendingHeadDiameter());
              newTestResult.setBendingAngle(resultDto.getBendingAngle());
            } else {
              newTestResult.setBendingHeadDiameter(null);
              newTestResult.setBendingAngle(null);
            }
          }
          testSampleGroup.getTestResults().add(newTestResult);
        } else { // Existing TestResult
          testSampleGroup.getTestResults().stream()
              .filter(existingResult -> existingResult.getId().equals(resultDto.getId()))
              .findFirst().ifPresent(existingResult -> {
                BeanUtils.copyProperties(resultDto, existingResult, "id", "testItemSettingId");
                if (resultDto.getTestItemSettingId() != null) {
                  TestItemSetting testItemSetting = testItemSettingRepository
                      .findById(resultDto.getTestItemSettingId())
                      .orElseThrow(() -> new EntityNotFoundException("TestItemSetting not found"));
                  existingResult.setTestItemSetting(testItemSetting);

                  // Apply conditional logic for bending properties based on test item code
                  if (TestItemCodeConstants.JI_XIAN_KANG_LA_QIANG_DU
                      .equals(testItemSetting.getTestItemCode())) {
                    existingResult.setBendingHeadDiameter(resultDto.getBendingHeadDiameter());
                    existingResult.setBendingAngle(resultDto.getBendingAngle());
                  } else {
                    existingResult.setBendingHeadDiameter(null);
                    existingResult.setBendingAngle(null);
                  }
                }
              });
        }
      });
    } else {
      testSampleGroup.setTestResults(new ArrayList<>());
    }

    TestSampleGroup updatedSampleGroup = testSampleGroupRepository.save(testSampleGroup);
    return convertToDto(updatedSampleGroup);
  }

  @Transactional
  public void deleteTestSampleGroup(Long sampleGroupId) {
    if (!testSampleGroupRepository.existsById(sampleGroupId)) {
      throw new EntityNotFoundException("TestSampleGroup not found with id: " + sampleGroupId);
    }
    testSampleGroupRepository.deleteById(sampleGroupId);
  }

  private void applyConditionalLogicToSampleGroup(TestSampleGroup sampleGroup, String reportCode) {
    if (ReportCodeConstants.GANGJJXLJ.equals(reportCode)) {
      sampleGroup.setWeldingMethod(null);
      sampleGroup.setWelderName(null);
      sampleGroup.setWelderCertificateNumber(null);
    } else if (ReportCodeConstants.GANGJHJ.equals(reportCode)) {
      sampleGroup.setJointType(null);
      sampleGroup.setJointGrade(null);
    }
  }

  public TestSampleGroupResponseDto convertToDto(TestSampleGroup testSampleGroup) {
    TestSampleGroupResponseDto dto = new TestSampleGroupResponseDto();
    BeanUtils.copyProperties(testSampleGroup, dto);

    if (testSampleGroup.getTestResults() != null) {
      List<TestResultDto> testResultDtos =
          testSampleGroup.getTestResults().stream().map(testResult -> {
            TestResultDto testResultDto = new TestResultDto();
            BeanUtils.copyProperties(testResult, testResultDto);
            testResultDto.setTestItemSettingId(
                testResult.getTestItemSetting() != null ? testResult.getTestItemSetting().getId()
                    : null);
            return testResultDto;
          }).collect(Collectors.toList());
      dto.setTestResults(testResultDtos);
    }
    return dto;
  }
}
