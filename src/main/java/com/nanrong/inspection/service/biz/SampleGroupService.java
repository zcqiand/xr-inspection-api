package com.nanrong.inspection.service.biz;

import com.nanrong.inspection.domain.biz.SampleGroup;
import com.nanrong.inspection.dto.biz.SampleGroupDTO;

public interface SampleGroupService {
    // 接收样品
    SampleGroup receiveSample(SampleGroupDTO sampleDTO);
    
    // 更新样品状态
    void updateSampleStatus(Long id, String status);
    
    // 获取样品信息
    SampleGroup getSampleByBarcode(String barcode);
}