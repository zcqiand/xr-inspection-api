package com.nanrong.inspection.service;

import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.nanrong.inspection.dto.test_report.TestReportApproveRequestDto;
import com.nanrong.inspection.dto.test_report.TestReportCreateRequestDto;
import com.nanrong.inspection.dto.test_report.TestReportDataEntryRequestDto;
import com.nanrong.inspection.dto.test_report.TestReportResponseDto;
import com.nanrong.inspection.dto.test_report.TestReportReviewRequestDto;
import com.nanrong.inspection.dto.test_report.TestReportUpdateRequestDto;
import com.nanrong.inspection.dto.test_sample_groups.TestSampleGroupResponseDto;
import com.nanrong.inspection.model.TestItemSetting;
import com.nanrong.inspection.model.TestReport;
import com.nanrong.inspection.model.TestReportNameSetting;
import com.nanrong.inspection.model.TestResult;
import com.nanrong.inspection.model.TestingAgencyInfo;
import com.nanrong.inspection.repository.TestItemSettingRepository;
import com.nanrong.inspection.repository.TestReportNameSettingRepository;
import com.nanrong.inspection.repository.TestReportRepository;
import com.nanrong.inspection.repository.TestResultRepository;
import com.nanrong.inspection.util.ReportCodeConstants;
import com.nanrong.inspection.util.TestItemCodeConstants;

@Service
public class TestReportService {

  @Autowired
  private TestReportRepository testReportRepository;
  @Autowired
  private TestReportNameSettingRepository testReportNameSettingRepository;
  @Autowired
  private TestItemSettingRepository testItemSettingRepository;
  @Autowired
  private TestSampleGroupService testSampleGroupService;
  @Autowired
  private TestResultRepository testResultRepository;

  @Transactional
  public TestReportResponseDto createTestReport(TestReportCreateRequestDto requestDto) {
    TestReport testReport = new TestReport();
    BeanUtils.copyProperties(requestDto, testReport);

    if (requestDto.getReportNameSettingId() != null) {
      TestReportNameSetting reportNameSetting =
          testReportNameSettingRepository.findById(requestDto.getReportNameSettingId())
              .orElseThrow(() -> new EntityNotFoundException("TestReportNameSetting not found"));
      testReport.setReportNameSetting(reportNameSetting);
    }

    TestReport savedReport = testReportRepository.save(testReport);
    return convertToDto(savedReport);
  }

  @Transactional
  public TestReportResponseDto updateTestReport(Long reportId,
      TestReportUpdateRequestDto requestDto) {
    TestReport testReport = testReportRepository.findById(reportId).orElseThrow(
        () -> new EntityNotFoundException("TestReport not found with id: " + reportId));

    Optional.ofNullable(requestDto.getReportNumber()).ifPresent(testReport::setReportNumber);
    Optional.ofNullable(requestDto.getReportNameSettingId()).ifPresent(id -> {
      TestReportNameSetting reportNameSetting = testReportNameSettingRepository.findById(id)
          .orElseThrow(() -> new EntityNotFoundException("TestReportNameSetting not found"));
      testReport.setReportNameSetting(reportNameSetting);
    });
    Optional.ofNullable(requestDto.getTestingCategory()).ifPresent(testReport::setTestingCategory);
    Optional.ofNullable(requestDto.getProjectName()).ifPresent(testReport::setProjectName);
    Optional.ofNullable(requestDto.getConstructionUnit())
        .ifPresent(testReport::setConstructionUnit);
    Optional.ofNullable(requestDto.getEngineeringLocation())
        .ifPresent(testReport::setEngineeringLocation);
    Optional.ofNullable(requestDto.getClientUnit()).ifPresent(testReport::setClientUnit);
    Optional.ofNullable(requestDto.getWitnessUnit()).ifPresent(testReport::setWitnessUnit);
    Optional.ofNullable(requestDto.getWitnessPerson()).ifPresent(testReport::setWitnessPerson);
    Optional.ofNullable(requestDto.getSampleSource()).ifPresent(testReport::setSampleSource);
    Optional.ofNullable(requestDto.getReceivingDate()).ifPresent(testReport::setReceivingDate);
    Optional.ofNullable(requestDto.getJudgmentBasis()).ifPresent(testReport::setJudgmentBasis);
    Optional.ofNullable(requestDto.getRemarks()).ifPresent(testReport::setRemarks);

    TestReport updatedReport = testReportRepository.save(testReport);
    return convertToDto(updatedReport);
  }

  @Transactional
  public void deleteTestReport(Long reportId) {
    if (!testReportRepository.existsById(reportId)) {
      throw new EntityNotFoundException("TestReport not found with id: " + reportId);
    }
    testReportRepository.deleteById(reportId);
  }

  @Transactional
  public TestReportResponseDto updateTestReportDataEntry(Long reportId,
      TestReportDataEntryRequestDto requestDto) {
    TestReport testReport = testReportRepository.findById(reportId).orElseThrow(
        () -> new EntityNotFoundException("TestReport not found with id: " + reportId));

    Optional.ofNullable(requestDto.getTestingDate()).ifPresent(testReport::setTestingDate);
    Optional.ofNullable(requestDto.getTestingAddress()).ifPresent(testReport::setTestingAddress);
    Optional.ofNullable(requestDto.getTestingEnvironment())
        .ifPresent(testReport::setTestingEnvironment);
    Optional.ofNullable(requestDto.getMainEquipment()).ifPresent(testReport::setMainEquipment);
    Optional.ofNullable(requestDto.getConclusion()).ifPresent(testReport::setConclusion);
    Optional.ofNullable(requestDto.getRemarks()).ifPresent(testReport::setRemarks);
    Optional.ofNullable(requestDto.getTestedBy()).ifPresent(testReport::setTestedBy);

    TestReport updatedReport = testReportRepository.save(testReport);
    return convertToDto(updatedReport);
  }

  @Transactional
  public TestReportResponseDto reviewTestReport(Long reportId,
      TestReportReviewRequestDto requestDto) {
    TestReport testReport = testReportRepository.findById(reportId).orElseThrow(
        () -> new EntityNotFoundException("TestReport not found with id: " + reportId));

    Optional.ofNullable(requestDto.getReviewedBy()).ifPresent(testReport::setReviewedBy);

    TestReport updatedReport = testReportRepository.save(testReport);
    return convertToDto(updatedReport);
  }

  @Transactional
  public TestReportResponseDto approveTestReport(Long reportId,
      TestReportApproveRequestDto requestDto) {
    TestReport testReport = testReportRepository.findById(reportId).orElseThrow(
        () -> new EntityNotFoundException("TestReport not found with id: " + reportId));

    Optional.ofNullable(requestDto.getApprovedBy()).ifPresent(testReport::setApprovedBy);
    Optional.ofNullable(requestDto.getIssueDate()).ifPresent(testReport::setIssueDate);

    TestReport updatedReport = testReportRepository.save(testReport);
    return convertToDto(updatedReport);
  }

  public TestReportResponseDto getTestReportById(Long id) {
    TestReport testReport = testReportRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("TestReport not found with id: " + id));
    return convertToDto(testReport);
  }

  public List<TestReportResponseDto> getAllTestReports() {
    return testReportRepository.findAll().stream().map(this::convertToDto)
        .collect(Collectors.toList());
  }

  private TestReportResponseDto convertToDto(TestReport testReport) {
    TestReportResponseDto dto = new TestReportResponseDto();
    BeanUtils.copyProperties(testReport, dto);

    if (testReport.getTestingAgencyInfo() != null) {
      TestReportResponseDto.TestingAgencyInfoResponseDto agencyDto =
          new TestReportResponseDto.TestingAgencyInfoResponseDto();
      BeanUtils.copyProperties(testReport.getTestingAgencyInfo(), agencyDto);
      dto.setTestingAgencyInfo(agencyDto);
    }

    if (testReport.getSampleGroups() != null) {
      List<TestSampleGroupResponseDto> sampleGroupDtos = testReport.getSampleGroups().stream()
          .map(testSampleGroupService::convertToDto).collect(Collectors.toList());
      dto.setSampleGroups(sampleGroupDtos);
    }
    return dto;
  }
}
