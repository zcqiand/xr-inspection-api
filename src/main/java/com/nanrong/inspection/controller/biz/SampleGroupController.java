package com.nanrong.inspection.controller.biz;

import com.nanrong.inspection.domain.biz.SampleGroup;
import com.nanrong.inspection.domain.biz.SampleGroup.SampleStatus;
import com.nanrong.inspection.dto.biz.SampleGroupDTO;
import com.nanrong.inspection.service.biz.SampleGroupService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/biz/sample-group")
@Tag(name = "样品组管理", description = "样品组管理接口")
public class SampleGroupController {

    private final SampleGroupService sampleGroupService;

    @Autowired
    public SampleGroupController(SampleGroupService sampleGroupService) {
        this.sampleGroupService = sampleGroupService;
    }

    @GetMapping
    @Operation(summary = "获取样品组列表", description = "获取所有样品组信息")
    public ResponseEntity<List<SampleGroup>> getAllSampleGroups() {
        return ResponseEntity.ok(sampleGroupService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "获取样品组详情", description = "根据ID获取样品组详细信息")
    public ResponseEntity<SampleGroup> getSampleGroupById(@PathVariable Long id) {
        return sampleGroupService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Sample group not found with id: " + id));
    }

    @PostMapping
    @Operation(summary = "创建样品组", description = "创建新的样品组")
    public ResponseEntity<SampleGroup> createSampleGroup(@RequestBody SampleGroupDTO sampleGroupDTO) {
        SampleGroup sampleGroup = sampleGroupService.createSampleGroup(sampleGroupDTO);
        return ResponseEntity.ok(sampleGroup);
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新样品组", description = "更新指定样品组的全部信息")
    public ResponseEntity<SampleGroup> updateSampleGroup(
            @PathVariable Long id,
            @RequestBody SampleGroupDTO sampleGroupDTO) {
        SampleGroup updatedSampleGroup = sampleGroupService.updateSampleGroup(id, sampleGroupDTO);
        return ResponseEntity.ok(updatedSampleGroup);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除样品组", description = "删除指定样品组")
    public ResponseEntity<Void> deleteSampleGroup(@PathVariable Long id) {
        sampleGroupService.deleteSampleGroup(id);
        return ResponseEntity.noContent().build();
    }
}