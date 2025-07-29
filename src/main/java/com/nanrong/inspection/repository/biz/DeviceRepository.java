package com.nanrong.inspection.repository.biz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nanrong.inspection.domain.biz.Device;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
}