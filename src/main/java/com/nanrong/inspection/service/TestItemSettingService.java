package com.nanrong.inspection.service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.nanrong.inspection.dto.test_item_setting.TestItemSettingCreateRequestDto;
import com.nanrong.inspection.dto.test_item_setting.TestItemSettingResponseDto;
import com.nanrong.inspection.dto.test_item_setting.TestItemSettingUpdateRequestDto;
import com.nanrong.inspection.model.TestItemSetting;
import com.nanrong.inspection.repository.TestItemSettingRepository;

@Service
public class TestItemSettingService {

  @Autowired
  private TestItemSettingRepository testItemSettingRepository;

  @Transactional
  public TestItemSettingResponseDto createTestItemSetting(
      TestItemSettingCreateRequestDto requestDto) {
    TestItemSetting testItemSetting = new TestItemSetting();
    BeanUtils.copyProperties(requestDto, testItemSetting);
    Optional.ofNullable(requestDto.getSortOrder()).ifPresent(testItemSetting::setSortOrder);
    TestItemSetting savedSetting = testItemSettingRepository.save(testItemSetting);
    return convertToDto(savedSetting);
  }

  @Transactional
  public TestItemSettingResponseDto updateTestItemSetting(Long id,
      TestItemSettingUpdateRequestDto requestDto) {
    TestItemSetting testItemSetting = testItemSettingRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("TestItemSetting not found with id: " + id));

    Optional.ofNullable(requestDto.getTestItemType()).ifPresent(testItemSetting::setTestItemType);
    Optional.ofNullable(requestDto.getTestItemCode()).ifPresent(testItemSetting::setTestItemCode);
    Optional.ofNullable(requestDto.getTestItemName()).ifPresent(testItemSetting::setTestItemName);
    Optional.ofNullable(requestDto.getTestItemUnit()).ifPresent(testItemSetting::setTestItemUnit);
    Optional.ofNullable(requestDto.getSortOrder()).ifPresent(testItemSetting::setSortOrder);

    TestItemSetting updatedSetting = testItemSettingRepository.save(testItemSetting);
    return convertToDto(updatedSetting);
  }

  public TestItemSettingResponseDto getTestItemSettingById(Long id) {
    TestItemSetting testItemSetting = testItemSettingRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("TestItemSetting not found with id: " + id));
    return convertToDto(testItemSetting);
  }

  public List<TestItemSettingResponseDto> getAllTestItemSettings() {
    return testItemSettingRepository
        .findAll(org.springframework.data.domain.Sort.by(TestItemSetting.Fields.sortOrder)).stream()
        .map(this::convertToDto).collect(Collectors.toList());
  }

  @Transactional
  public void deleteTestItemSetting(Long id) {
    if (!testItemSettingRepository.existsById(id)) {
      throw new EntityNotFoundException("TestItemSetting not found with id: " + id);
    }
    testItemSettingRepository.deleteById(id);
  }

  private TestItemSettingResponseDto convertToDto(TestItemSetting testItemSetting) {
    TestItemSettingResponseDto dto = new TestItemSettingResponseDto();
    BeanUtils.copyProperties(testItemSetting, dto);
    return dto;
  }

  public boolean existsByTestItemName(String testItemName) {
    return testItemSettingRepository.existsByTestItemName(testItemName);
  }
}
