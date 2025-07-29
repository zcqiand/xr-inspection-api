package com.nanrong.inspection.controller.biz;

import com.nanrong.inspection.domain.biz.Device;
import com.nanrong.inspection.dto.biz.BaseDeviceDTO;
import com.nanrong.inspection.service.biz.DeviceService;
import com.nanrong.inspection.util.DTOConverter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/biz/device")
@Tag(name = "设备管理", description = "设备管理接口")
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping
    @Operation(summary = "获取所有设备列表")
    public ResponseEntity<List<BaseDeviceDTO>> getAllDevices() {
        List<Device> devices = deviceService.findAllDevices();
        List<BaseDeviceDTO> deviceDTOs = devices.stream()
                .map(DTOConverter::convertToDeviceDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(deviceDTOs);
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据ID获取设备详情")
    public ResponseEntity<BaseDeviceDTO> getDeviceById(@PathVariable Long id) {
        Device device = deviceService.findDeviceById(id);
        return ResponseEntity.ok(DTOConverter.convertToDeviceDTO(device));
    }

    @PostMapping
    @Operation(summary = "创建新设备")
    public ResponseEntity<BaseDeviceDTO> createDevice(@RequestBody BaseDeviceDTO deviceDTO) {
        Device device = DTOConverter.convertToDeviceEntity(deviceDTO);
        Device savedDevice = deviceService.saveDevice(device);
        return ResponseEntity.ok(DTOConverter.convertToDeviceDTO(savedDevice));
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新设备信息")
    public ResponseEntity<BaseDeviceDTO> updateDevice(
            @PathVariable Long id,
            @RequestBody BaseDeviceDTO deviceDTO) {
        Device device = DTOConverter.convertToDeviceEntity(deviceDTO);
        Device updatedDevice = deviceService.updateDevice(id, device);
        return ResponseEntity.ok(DTOConverter.convertToDeviceDTO(updatedDevice));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除设备")
    public ResponseEntity<Void> deleteDevice(@PathVariable Long id) {
        deviceService.deleteDevice(id);
        return ResponseEntity.noContent().build();
    }
}