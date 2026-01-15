package com.device.management.repository;

import com.device.management.entity.DeviceIp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceIpRepository extends JpaRepository<DeviceIp, Integer> {
}
