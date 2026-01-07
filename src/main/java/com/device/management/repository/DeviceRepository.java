package com.device.management.repository;

import com.device.management.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository
        extends JpaRepository<Device, String>,
        JpaSpecificationExecutor<Device> {
}