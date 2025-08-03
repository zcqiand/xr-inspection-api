package com.nanrong.inspection.service.biz;

import com.nanrong.inspection.domain.biz.SampleGroup;
import com.nanrong.inspection.domain.biz.SampleGroup.SampleStatus;
import com.nanrong.inspection.dto.biz.SampleGroupDTO;

import java.util.List;
import java.util.Optional;

public interface SampleGroupService {
    List<SampleGroup> findAll();
    Optional<SampleGroup> findById(Long id);
    SampleGroup createSampleGroup(SampleGroupDTO sampleGroupDTO);
    SampleGroup updateSampleGroup(Long id, SampleGroupDTO sampleGroupDTO);
    void deleteSampleGroup(Long id);
}