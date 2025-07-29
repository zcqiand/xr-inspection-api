package com.nanrong.inspection.service.biz;

import com.nanrong.inspection.domain.biz.BMethod;
import com.nanrong.inspection.dto.biz.BaseMethodDTO;

import java.util.List;

public interface BMethodService {
    // 创建方法
    BMethod createMethod(BaseMethodDTO methodDTO);
    
    // 更新方法
    BMethod updateMethod(Long id, BaseMethodDTO methodDTO);
    
    // 删除方法
    void deleteMethod(Long id);
    
    // 获取所有方法
    List<BMethod> findAllMethods();
    
    // 根据ID获取方法
    BMethod findMethodById(Long id);
}