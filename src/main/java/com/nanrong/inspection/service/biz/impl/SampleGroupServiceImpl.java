package com.nanrong.inspection.service.biz.impl;

import com.nanrong.inspection.domain.biz.SampleGroup;
import com.nanrong.inspection.dto.biz.SampleGroupDTO;
import com.nanrong.inspection.repository.biz.SampleGroupRepository;
import com.nanrong.inspection.service.biz.SampleGroupService;
import com.nanrong.inspection.util.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SampleGroupServiceImpl implements SampleGroupService {

    private final SampleGroupRepository sampleRepository;
    
    @Autowired
    public SampleGroupServiceImpl(SampleGroupRepository sampleRepository) {
        this.sampleRepository = sampleRepository;
    }

    @Override
    public SampleGroup receiveSample(SampleGroupDTO sampleDTO) {
        SampleGroup sample = DTOConverter.convertToEntity(sampleDTO, SampleGroup.class);
        return sampleRepository.save(sample);
    }

    @Override
    public void updateSampleStatus(Long id, String status) {
        SampleGroup sample = sampleRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Sample not found"));
        sample.setStatus(SampleGroup.SampleStatus.valueOf(status));
        sampleRepository.save(sample);
    }

    @Override
    public SampleGroup getSampleByBarcode(String barcode) {
        return sampleRepository.findByBarcode(barcode);
    }
}