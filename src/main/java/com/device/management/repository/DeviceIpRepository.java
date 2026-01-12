package com.device.management.repository;

import com.device.management.entity.DeviceIp;
import com.device.management.entity.DevicePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface DeviceIpRepository
        extends JpaRepository<DeviceIp, Integer>,
        JpaSpecificationExecutor<DeviceIp>,
        QueryByExampleExecutor<DeviceIp> {
}
