package com.nanrong.inspection.service.biz.impl;

import com.nanrong.inspection.domain.biz.BMethod;
import com.nanrong.inspection.dto.biz.BaseMethodDTO;
import com.nanrong.inspection.repository.biz.BMethodRepository;
import com.nanrong.inspection.service.biz.BMethodService;
import com.nanrong.inspection.util.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BMethodServiceImpl implements BMethodService {

    private final BMethodRepository methodRepository;

    @Autowired
    public BMethodServiceImpl(BMethodRepository methodRepository) {
        this.methodRepository = methodRepository;
    }

    @Override
    public BMethod createMethod(BaseMethodDTO methodDTO) {
        BMethod method = DTOConverter.convertToEntity(methodDTO, BMethod.class);
        return methodRepository.save(method);
    }

    @Override
    public BMethod updateMethod(Long id, BaseMethodDTO methodDTO) {
        BMethod existingMethod = methodRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Method not found"));
        DTOConverter.updateEntityFromDTO(methodDTO, existingMethod);
        return methodRepository.save(existingMethod);
    }

    @Override
    public void deleteMethod(Long id) {
        methodRepository.deleteById(id);
    }

    @Override
    public List<BMethod> findAllMethods() {
        return methodRepository.findAll();
    }

    @Override
    public BMethod findMethodById(Long id) {
        return methodRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Method not found"));
    }
}