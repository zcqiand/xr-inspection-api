package com.nanrong.inspection.controller.biz;

import com.nanrong.inspection.domain.biz.SampleGroup;
import com.nanrong.inspection.dto.biz.SampleGroupDTO;
import com.nanrong.inspection.service.biz.SampleGroupService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/biz/sample-group")
@Tag(name = "样本组管理", description = "样本组管理接口")
public class SampleGroupController {

    private final SampleGroupService sampleService;

    @Autowired
    public SampleGroupController(SampleGroupService sampleService) {
        this.sampleService = sampleService;
    }

    @PostMapping
    @Operation(summary = "接收样本", description = "接收并创建新的样本组")
    public ResponseEntity<SampleGroup> receiveSample(@RequestBody SampleGroupDTO sampleDTO) {
        SampleGroup sample = sampleService.receiveSample(sampleDTO);
        return ResponseEntity.ok(sample);
    }

    @GetMapping("/{barcode}")
    @Operation(summary = "根据条码获取样本", description = "根据样本条码获取样本组信息")
    public ResponseEntity<SampleGroup> getSampleByBarcode(@PathVariable String barcode) {
        SampleGroup sample = sampleService.getSampleByBarcode(barcode);
        return ResponseEntity.ok(sample);
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "更新样本状态", description = "更新指定样本组的状态")
    public ResponseEntity<Void> updateSampleStatus(@PathVariable Long id, @RequestParam String status) {
        sampleService.updateSampleStatus(id, status);
        return ResponseEntity.ok().build();
    }
}