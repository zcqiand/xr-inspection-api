package com.nanrong.inspection.service.biz;

import java.util.List;

import com.nanrong.inspection.domain.biz.Device;

public interface DeviceService {
    List<Device> findAllDevices();
    Device findDeviceById(Long id);
    Device saveDevice(Device device);
    Device updateDevice(Long id, Device device);
    void deleteDevice(Long id);
}