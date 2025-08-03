package com.nanrong.inspection.service.biz.impl;

import com.nanrong.inspection.domain.biz.SampleGroup;
import com.nanrong.inspection.dto.biz.SampleGroupDTO;
import com.nanrong.inspection.repository.biz.SampleGroupRepository;
import com.nanrong.inspection.service.biz.SampleGroupService;
import com.nanrong.inspection.util.DTOConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SampleGroupServiceImpl implements SampleGroupService {

    private final SampleGroupRepository sampleGroupRepository;
    
    @Autowired
    public SampleGroupServiceImpl(SampleGroupRepository sampleGroupRepository) {
        this.sampleGroupRepository = sampleGroupRepository;
    }

    @Override
    public List<SampleGroup> findAll() {
        return sampleGroupRepository.findAll();
    }

    @Override
    public Optional<SampleGroup> findById(Long id) {
        return sampleGroupRepository.findById(id);
    }

    @Override
    public SampleGroup createSampleGroup(SampleGroupDTO sampleGroupDTO) {
        SampleGroup sampleGroup = DTOConverter.convertToEntity(sampleGroupDTO, SampleGroup.class);
        return sampleGroupRepository.save(sampleGroup);
    }

    @Override
    public SampleGroup updateSampleGroup(Long id, SampleGroupDTO sampleGroupDTO) {
        SampleGroup existingSampleGroup = sampleGroupRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Sample group not found with id: " + id));
        
        // 更新字段
        existingSampleGroup.setBarcode(sampleGroupDTO.getBarcode());
        existingSampleGroup.setReceivedTime(sampleGroupDTO.getReceivedTime());
        existingSampleGroup.setStorageTemperature(sampleGroupDTO.getStorageTemperature());
        existingSampleGroup.setStatus(sampleGroupDTO.getStatus());
        
        return sampleGroupRepository.save(existingSampleGroup);
    }

    @Override
    public void deleteSampleGroup(Long id) {
        if (!sampleGroupRepository.existsById(id)) {
            throw new RuntimeException("Sample group not found with id: " + id);
        }
        sampleGroupRepository.deleteById(id);
    }
}