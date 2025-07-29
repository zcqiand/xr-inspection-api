package com.nanrong.inspection.service.biz.impl;

import com.nanrong.inspection.domain.biz.Device;
import com.nanrong.inspection.repository.biz.DeviceRepository;
import com.nanrong.inspection.service.biz.DeviceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceServiceImpl(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public List<Device> findAllDevices() {
        return deviceRepository.findAll();
    }

    @Override
    public Device findDeviceById(Long id) {
        Optional<Device> device = deviceRepository.findById(id);
        return device.orElseThrow(() -> new RuntimeException("Device not found with id: " + id));
    }

    @Override
    public Device saveDevice(Device device) {
        return deviceRepository.save(device);
    }

    @Override
    public Device updateDevice(Long id, Device deviceDetails) {
        Device device = findDeviceById(id);
        device.setName(deviceDetails.getName());
        device.setModel(deviceDetails.getModel());
        device.setSerialNumber(deviceDetails.getSerialNumber());
        device.setPurchaseDate(deviceDetails.getPurchaseDate());
        device.setStatus(deviceDetails.getStatus());
        device.setLastMaintenanceDate(deviceDetails.getLastMaintenanceDate());
        device.setLocation(deviceDetails.getLocation());
        device.setResponsiblePerson(deviceDetails.getResponsiblePerson());
        return deviceRepository.save(device);
    }

    @Override
    public void deleteDevice(Long id) {
        deviceRepository.deleteById(id);
    }
}