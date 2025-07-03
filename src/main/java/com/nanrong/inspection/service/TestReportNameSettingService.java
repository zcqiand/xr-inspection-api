package com.nanrong.inspection.service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.nanrong.inspection.dto.test_report_name_setting.TestReportNameSettingCreateRequestDto;
import com.nanrong.inspection.dto.test_report_name_setting.TestReportNameSettingResponseDto;
import com.nanrong.inspection.dto.test_report_name_setting.TestReportNameSettingUpdateRequestDto;
import com.nanrong.inspection.model.TestReportNameSetting;
import com.nanrong.inspection.repository.TestReportNameSettingRepository;

@Service
public class TestReportNameSettingService {

  @Autowired
  private TestReportNameSettingRepository testReportNameSettingRepository;

  @Transactional
  public TestReportNameSettingResponseDto createTestReportNameSetting(
      TestReportNameSettingCreateRequestDto requestDto) {
    TestReportNameSetting testReportNameSetting = new TestReportNameSetting();
    BeanUtils.copyProperties(requestDto, testReportNameSetting);
    Optional.ofNullable(requestDto.getSortOrder()).ifPresent(testReportNameSetting::setSortOrder);
    TestReportNameSetting savedSetting =
        testReportNameSettingRepository.save(testReportNameSetting);
    return convertToDto(savedSetting);
  }

  @Transactional
  public TestReportNameSettingResponseDto updateTestReportNameSetting(Long id,
      TestReportNameSettingUpdateRequestDto requestDto) {
    TestReportNameSetting testReportNameSetting =
        testReportNameSettingRepository.findById(id).orElseThrow(
            () -> new EntityNotFoundException("TestReportNameSetting not found with id: " + id));

    Optional.ofNullable(requestDto.getReportCode()).ifPresent(testReportNameSetting::setReportCode);
    Optional.ofNullable(requestDto.getReportAbbreviation())
        .ifPresent(testReportNameSetting::setReportAbbreviation);
    Optional.ofNullable(requestDto.getReportName()).ifPresent(testReportNameSetting::setReportName);
    Optional.ofNullable(requestDto.getSortOrder()).ifPresent(testReportNameSetting::setSortOrder);

    TestReportNameSetting updatedSetting =
        testReportNameSettingRepository.save(testReportNameSetting);
    return convertToDto(updatedSetting);
  }

  public TestReportNameSettingResponseDto getTestReportNameSettingById(Long id) {
    TestReportNameSetting testReportNameSetting =
        testReportNameSettingRepository.findById(id).orElseThrow(
            () -> new EntityNotFoundException("TestReportNameSetting not found with id: " + id));
    return convertToDto(testReportNameSetting);
  }

  public List<TestReportNameSettingResponseDto> getAllTestReportNameSettings() {
    return testReportNameSettingRepository
        .findAll(org.springframework.data.domain.Sort.by(TestReportNameSetting.Fields.sortOrder))
        .stream().map(this::convertToDto).collect(Collectors.toList());
  }

  @Transactional
  public void deleteTestReportNameSetting(Long id) {
    if (!testReportNameSettingRepository.existsById(id)) {
      throw new EntityNotFoundException("TestReportNameSetting not found with id: " + id);
    }
    testReportNameSettingRepository.deleteById(id);
  }

  private TestReportNameSettingResponseDto convertToDto(
      TestReportNameSetting testReportNameSetting) {
    TestReportNameSettingResponseDto dto = new TestReportNameSettingResponseDto();
    BeanUtils.copyProperties(testReportNameSetting, dto);
    return dto;
  }
}
